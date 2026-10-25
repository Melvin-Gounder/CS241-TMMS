package fj.usp.tmms.service;

import fj.usp.tmms.model.*;
import fj.usp.tmms.repository.*;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IncidentDetectionService {
    private final IncidentRepository incidents; private final AlertRepository alerts;
    private final Map<Long,LocalDateTime> lowSpeedSince=new ConcurrentHashMap<>();
    public IncidentDetectionService(IncidentRepository incidents,AlertRepository alerts){this.incidents=incidents;this.alerts=alerts;}
    public void observe(Junction junction,double speed,LocalDateTime now){
        if(speed>=10){lowSpeedSince.remove(junction.getId());return;}
        LocalDateTime since=lowSpeedSince.computeIfAbsent(junction.getId(),k->now);
        if(Duration.between(since,now).toSeconds()>15 && !incidents.existsByJunctionAndStatusNot(junction,IncidentStatus.RESOLVED)){
            Incident i=new Incident();i.setJunction(junction);i.setType("Low-speed congestion");i.setDescription("Automatic incident: speed stayed below 10 km/h for more than 15 seconds.");i.setSource("AUTO");i.setReportedAt(now);incidents.save(i);
            Alert a=new Alert();a.setTitle("Automatic traffic incident");a.setMessage(junction.getName()+": prolonged speed below 10 km/h detected.");a.setTargetRole("OPERATOR");alerts.save(a);
        }
    }
}
