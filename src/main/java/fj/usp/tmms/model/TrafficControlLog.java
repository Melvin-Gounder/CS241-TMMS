package fj.usp.tmms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="traffic_control_logs")
public class TrafficControlLog {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="junction_id") private Junction junction;
    @Column(name="action_type",nullable=false,length=60) private String actionType;
    @Column(nullable=false,length=600) private String details;
    @Column(name="performed_by",nullable=false,length=150) private String performedBy;
    @Column(name="created_at",nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Junction getJunction(){return junction;} public void setJunction(Junction v){junction=v;}
    public String getActionType(){return actionType;} public void setActionType(String v){actionType=v;}
    public String getDetails(){return details;} public void setDetails(String v){details=v;}
    public String getPerformedBy(){return performedBy;} public void setPerformedBy(String v){performedBy=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
