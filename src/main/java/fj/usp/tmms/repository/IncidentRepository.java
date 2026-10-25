package fj.usp.tmms.repository;
import fj.usp.tmms.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface IncidentRepository extends JpaRepository<Incident,Long>{ List<Incident> findTop20ByOrderByReportedAtDesc(); boolean existsByJunctionAndStatusNot(Junction junction, IncidentStatus status); }
