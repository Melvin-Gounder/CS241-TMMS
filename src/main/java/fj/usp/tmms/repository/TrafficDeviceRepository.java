package fj.usp.tmms.repository;
import fj.usp.tmms.model.TrafficDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface TrafficDeviceRepository extends JpaRepository<TrafficDevice,Long>{ List<TrafficDevice> findAllByOrderByIdAsc(); }
