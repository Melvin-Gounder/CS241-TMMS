package fj.usp.tmms.repository;
import fj.usp.tmms.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AlertRepository extends JpaRepository<Alert,Long>{ List<Alert> findTop20ByOrderByCreatedAtDesc(); }
