package fj.usp.tmms.service;

import fj.usp.tmms.model.*;
import fj.usp.tmms.repository.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TrafficSimulationService {
    public record LiveStatus(Long junctionId,String junction,int vehicleCount,double speedKmh,double occupancyPercent,int queueLength,String roadCondition,CongestionLevel congestion,int greenSeconds,boolean emergencyPriority,boolean manualOverride,LocalDateTime updatedAt){}
    private final JunctionRepository junctions; private final TrafficObservationRepository observations; private final IncidentDetectionService incidentDetection; private final EmergencyPriorityService priority;
    private final Map<Long,LiveStatus> latest=new ConcurrentHashMap<>(); private final Random random=new Random();
    public TrafficSimulationService(JunctionRepository junctions,TrafficObservationRepository observations,IncidentDetectionService incidentDetection,EmergencyPriorityService priority){this.junctions=junctions;this.observations=observations;this.incidentDetection=incidentDetection;this.priority=priority;}
    @Scheduled(fixedRate=5000,initialDelay=1500) @Transactional
    public void refresh(){
        LocalDateTime now=LocalDateTime.now();
        for(Junction j:junctions.findAllByOrderByDisplayOrderAsc()){
            double occupancy=20+random.nextDouble()*70; double speed=Math.max(4,68-occupancy*0.62+random.nextGaussian()*5); int queue=Math.max(0,(int)Math.round((occupancy-25)/5+random.nextGaussian()*2)); int vehicles=20+random.nextInt(81);
            CongestionLevel level=TrafficRules.classify(speed,occupancy); j.setCongestion(level); junctions.save(j); int green=TrafficRules.greenSecondsForQueue(queue); if(priority.isEmergencyActive(j.getId())) green=60;
            String road=random.nextDouble()<0.08?"WET":"NORMAL";
            LiveStatus s=new LiveStatus(j.getId(),j.getName(),vehicles,round(speed),round(occupancy),queue,road,level,green,priority.isEmergencyActive(j.getId()),priority.isOverrideActive(j.getId()),now); latest.put(j.getId(),s); incidentDetection.observe(j,speed,now);
        }
    }
    @Scheduled(fixedRate=30000,initialDelay=5000) @Transactional
    public void snapshot(){
        if(latest.isEmpty()) return;
        Map<Long,Junction> byId=new HashMap<>(); for(Junction j:junctions.findAll()) byId.put(j.getId(),j);
        for(LiveStatus s:latest.values()){Junction j=byId.get(s.junctionId());if(j==null)continue;TrafficObservation o=new TrafficObservation();o.setJunction(j);o.setObservedAt(s.updatedAt());o.setVehicleCount(s.vehicleCount());o.setSpeedKmh(s.speedKmh());o.setOccupancyPercent(s.occupancyPercent());o.setQueueLength(s.queueLength());o.setRoadCondition(s.roadCondition());o.setCongestion(s.congestion());o.setGreenSeconds(s.greenSeconds());observations.save(o);}
    }
    public List<LiveStatus> current(){
        List<Junction> ordered=junctions.findAllByOrderByDisplayOrderAsc(); List<LiveStatus> result=new ArrayList<>();
        for(Junction j:ordered){LiveStatus s=latest.get(j.getId()); if(s==null)s=new LiveStatus(j.getId(),j.getName(),0,0,0,0,"NORMAL",j.getCongestion(),15,false,false,LocalDateTime.now()); result.add(s);} return result;
    }
    private static double round(double v){return Math.round(v*10.0)/10.0;}
}
