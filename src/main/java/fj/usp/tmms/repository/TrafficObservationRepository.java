package fj.usp.tmms.repository;
import fj.usp.tmms.model.TrafficObservation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TrafficObservationRepository extends JpaRepository<TrafficObservation,Long>{}
