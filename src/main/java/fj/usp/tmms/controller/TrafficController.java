package fj.usp.tmms.controller;

import fj.usp.tmms.model.*;
import fj.usp.tmms.repository.*;
import fj.usp.tmms.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class TrafficController {
    private final TrafficSimulationService traffic; private final JunctionRepository junctions; private final EmergencyPriorityService priority; private final IncidentRepository incidents;
    public TrafficController(TrafficSimulationService t,JunctionRepository j,EmergencyPriorityService p,IncidentRepository i){traffic=t;junctions=j;priority=p;incidents=i;}
    @GetMapping("/traffic") public String traffic(Model m){m.addAttribute("statuses",traffic.current());m.addAttribute("incidents",incidents.findTop20ByOrderByReportedAtDesc());return "traffic";}
    @ResponseBody @GetMapping("/api/public/status") public List<TrafficSimulationService.LiveStatus> status(){return traffic.current();}
    @PostMapping("/emergency/priority") public String emergency(@RequestParam Long junctionId,@RequestParam String vehicleType,@RequestParam String vehicleId,Authentication auth,Model m){try{Junction j=junctions.findById(junctionId).orElseThrow();priority.startEmergency(j,vehicleType,vehicleId,auth.getName());return "redirect:/emergency?success";}catch(RuntimeException ex){return "redirect:/emergency?error";}}
    @PostMapping("/operator/override") public String override(@RequestParam Long junctionId,Authentication auth){try{priority.startManualOverride(junctions.findById(junctionId).orElseThrow(),auth.getName());return "redirect:/operator?override";}catch(RuntimeException ex){return "redirect:/operator?blocked";}}
    @PostMapping("/operator/incidents") public String report(@RequestParam Long junctionId,@RequestParam String description,Authentication auth){Junction j=junctions.findById(junctionId).orElseThrow();Incident i=new Incident();i.setJunction(j);i.setType("Manual report");i.setDescription(description);i.setSource("MANUAL:"+auth.getName());incidents.save(i);return "redirect:/operator?reported";}
}
