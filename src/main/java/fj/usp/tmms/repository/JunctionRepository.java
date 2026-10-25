package fj.usp.tmms.repository;
import fj.usp.tmms.model.Junction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface JunctionRepository extends JpaRepository<Junction,Long>{ List<Junction> findAllByOrderByDisplayOrderAsc(); Optional<Junction> findByNameIgnoreCase(String name); }
