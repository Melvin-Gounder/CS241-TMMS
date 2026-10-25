package fj.usp.tmms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="traffic_devices")
public class TrafficDevice {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="junction_id") private Junction junction;
    @Column(name="device_name",nullable=false,length=120) private String deviceName;
    @Column(name="device_type",nullable=false,length=60) private String deviceType;
    @Column(nullable=false,length=30) private String status;
    @Column(name="last_seen",nullable=false) private LocalDateTime lastSeen=LocalDateTime.now();
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Junction getJunction(){return junction;} public void setJunction(Junction v){junction=v;}
    public String getDeviceName(){return deviceName;} public void setDeviceName(String v){deviceName=v;}
    public String getDeviceType(){return deviceType;} public void setDeviceType(String v){deviceType=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public LocalDateTime getLastSeen(){return lastSeen;} public void setLastSeen(LocalDateTime v){lastSeen=v;}
}
