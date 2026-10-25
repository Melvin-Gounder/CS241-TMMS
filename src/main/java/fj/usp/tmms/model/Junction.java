package fj.usp.tmms.model;

import jakarta.persistence.*;

@Entity
@Table(name="junctions")
public class Junction {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=80) private String name;
    @Column(name="display_order", nullable=false) private int displayOrder;
    private Double latitude; private Double longitude;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private CongestionLevel congestion = CongestionLevel.GREEN;
    public Junction(){}
    public Junction(String name,int order,Double lat,Double lon){this.name=name;displayOrder=order;latitude=lat;longitude=lon;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public int getDisplayOrder(){return displayOrder;} public void setDisplayOrder(int v){displayOrder=v;}
    public Double getLatitude(){return latitude;} public void setLatitude(Double v){latitude=v;}
    public Double getLongitude(){return longitude;} public void setLongitude(Double v){longitude=v;}
    public CongestionLevel getCongestion(){return congestion;} public void setCongestion(CongestionLevel v){congestion=v;}
}
