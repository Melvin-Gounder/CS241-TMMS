package fj.usp.tmms.repository;
import fj.usp.tmms.model.TrafficControlLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface TrafficControlLogRepository extends JpaRepository<TrafficControlLog,Long>{ List<TrafficControlLog> findTop20ByOrderByCreatedAtDesc(); }
