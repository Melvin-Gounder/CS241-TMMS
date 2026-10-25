package fj.usp.tmms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="incidents")
public class Incident {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="junction_id") private Junction junction;
    @Column(nullable=false,length=60) private String type;
    @Column(nullable=false,length=500) private String description;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private IncidentStatus status=IncidentStatus.OPEN;
    @Column(nullable=false,length=30) private String source;
    @Column(name="reported_at",nullable=false) private LocalDateTime reportedAt=LocalDateTime.now();
    @Column(name="resolved_at") private LocalDateTime resolvedAt;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Junction getJunction(){return junction;} public void setJunction(Junction v){junction=v;}
    public String getType(){return type;} public void setType(String v){type=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public IncidentStatus getStatus(){return status;} public void setStatus(IncidentStatus v){status=v;}
    public String getSource(){return source;} public void setSource(String v){source=v;}
    public LocalDateTime getReportedAt(){return reportedAt;} public void setReportedAt(LocalDateTime v){reportedAt=v;}
    public LocalDateTime getResolvedAt(){return resolvedAt;} public void setResolvedAt(LocalDateTime v){resolvedAt=v;}
}
