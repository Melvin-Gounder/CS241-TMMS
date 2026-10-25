package fj.usp.tmms.controller;

import fj.usp.tmms.repository.*;
import fj.usp.tmms.service.TrafficSimulationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final TrafficSimulationService traffic; private final IncidentRepository incidents; private final TrafficDeviceRepository devices; private final SystemUserRepository users; private final AlertRepository alerts; private final TrafficControlLogRepository logs;
    public DashboardController(TrafficSimulationService t,IncidentRepository i,TrafficDeviceRepository d,SystemUserRepository u,AlertRepository a,TrafficControlLogRepository l){traffic=t;incidents=i;devices=d;users=u;alerts=a;logs=l;}
    @GetMapping("/dashboard") public String dashboard(Authentication auth){var names=auth.getAuthorities().stream().map(Object::toString).toList();if(names.contains("ROLE_ADMIN"))return "redirect:/admin";if(names.contains("ROLE_OPERATOR"))return "redirect:/operator";if(names.contains("ROLE_AUTHORITY"))return "redirect:/authority";if(names.contains("ROLE_EMERGENCY"))return "redirect:/emergency";return "redirect:/traffic";}
    @GetMapping("/admin") public String admin(Model m){common(m);m.addAttribute("users",users.findAll());m.addAttribute("devices",devices.findAllByOrderByIdAsc());m.addAttribute("alerts",alerts.findTop20ByOrderByCreatedAtDesc());m.addAttribute("logs",logs.findTop20ByOrderByCreatedAtDesc());return "dashboard-admin";}
    @GetMapping("/operator") public String operator(Model m){common(m);m.addAttribute("logs",logs.findTop20ByOrderByCreatedAtDesc());return "dashboard-operator";}
    @GetMapping("/authority") public String authority(Model m){common(m);return "dashboard-authority";}
    @GetMapping("/emergency") public String emergency(Model m){common(m);return "dashboard-emergency";}
    private void common(Model m){m.addAttribute("statuses",traffic.current());m.addAttribute("incidents",incidents.findTop20ByOrderByReportedAtDesc());}
}
