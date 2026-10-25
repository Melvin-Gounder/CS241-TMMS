package fj.usp.tmms.repository;
import fj.usp.tmms.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SystemUserRepository extends JpaRepository<SystemUser,Long>{ Optional<SystemUser> findByEmailIgnoreCase(String email); boolean existsByEmailIgnoreCase(String email); }
