package fj.usp.tmms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="alerts")
public class Alert {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=150) private String title;
    @Column(nullable=false,length=600) private String message;
    @Column(name="target_role",nullable=false,length=30) private String targetRole;
    @Column(name="created_at",nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
    @Column(nullable=false) private boolean acknowledged=false;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getMessage(){return message;} public void setMessage(String v){message=v;}
    public String getTargetRole(){return targetRole;} public void setTargetRole(String v){targetRole=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public boolean isAcknowledged(){return acknowledged;} public void setAcknowledged(boolean v){acknowledged=v;}
}
