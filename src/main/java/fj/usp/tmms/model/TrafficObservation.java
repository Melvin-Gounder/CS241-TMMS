package fj.usp.tmms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="traffic_observations")
public class TrafficObservation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="junction_id") private Junction junction;
    @Column(name="observed_at",nullable=false) private LocalDateTime observedAt;
    @Column(name="vehicle_count",nullable=false) private int vehicleCount;
    @Column(name="speed_kmh",nullable=false) private double speedKmh;
    @Column(name="occupancy_percent",nullable=false) private double occupancyPercent;
    @Column(name="queue_length",nullable=false) private int queueLength;
    @Column(name="road_condition",nullable=false,length=40) private String roadCondition;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private CongestionLevel congestion;
    @Column(name="green_seconds",nullable=false) private int greenSeconds;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Junction getJunction(){return junction;} public void setJunction(Junction v){junction=v;}
    public LocalDateTime getObservedAt(){return observedAt;} public void setObservedAt(LocalDateTime v){observedAt=v;}
    public int getVehicleCount(){return vehicleCount;} public void setVehicleCount(int v){vehicleCount=v;}
    public double getSpeedKmh(){return speedKmh;} public void setSpeedKmh(double v){speedKmh=v;}
    public double getOccupancyPercent(){return occupancyPercent;} public void setOccupancyPercent(double v){occupancyPercent=v;}
    public int getQueueLength(){return queueLength;} public void setQueueLength(int v){queueLength=v;}
    public String getRoadCondition(){return roadCondition;} public void setRoadCondition(String v){roadCondition=v;}
    public CongestionLevel getCongestion(){return congestion;} public void setCongestion(CongestionLevel v){congestion=v;}
    public int getGreenSeconds(){return greenSeconds;} public void setGreenSeconds(int v){greenSeconds=v;}
}
