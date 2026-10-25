package fj.usp.tmms.config;

import fj.usp.tmms.model.*;
import fj.usp.tmms.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    @Bean CommandLineRunner seed(JunctionRepository junctions,SystemUserRepository users,TrafficDeviceRepository devices,PasswordEncoder encoder){return args->{
        seedJunction(junctions,"Nausori",1,-18.0248,178.5454); seedJunction(junctions,"Nakasi",2,-18.0718,178.5192); seedJunction(junctions,"Nasinu",3,-18.0740,178.4936); seedJunction(junctions,"Valelevu",4,-18.0913,178.4680);
        seedUser(users,encoder,"System Administrator","admin@tmms.local","Admin123!",Role.ADMIN); seedUser(users,encoder,"TMC Operator","operator@tmms.local","Operator123!",Role.OPERATOR); seedUser(users,encoder,"FRA/LTA Authority","authority@tmms.local","Authority123!",Role.AUTHORITY); seedUser(users,encoder,"Emergency Services","emergency@tmms.local","Emergency123!",Role.EMERGENCY); seedUser(users,encoder,"Public Demo","public@tmms.local","Public123!",Role.PUBLIC);
        if(devices.count()==0){for(Junction j:junctions.findAllByOrderByDisplayOrderAsc()){TrafficDevice d=new TrafficDevice();d.setJunction(j);d.setDeviceName(j.getName()+" Sensor 1");d.setDeviceType("TRAFFIC_SENSOR");d.setStatus("ONLINE");d.setLastSeen(LocalDateTime.now());devices.save(d);}}
    };}
    private static void seedJunction(JunctionRepository r,String n,int o,double lat,double lon){if(r.findByNameIgnoreCase(n).isEmpty())r.save(new Junction(n,o,lat,lon));}
    private static void seedUser(SystemUserRepository r,PasswordEncoder e,String name,String email,String pass,Role role){if(!r.existsByEmailIgnoreCase(email)){SystemUser u=new SystemUser();u.setFullName(name);u.setEmail(email);u.setPasswordHash(e.encode(pass));u.setRole(role);r.save(u);}}
}
