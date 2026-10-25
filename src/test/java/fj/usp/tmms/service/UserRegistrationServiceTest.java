package fj.usp.tmms.service;
import fj.usp.tmms.model.*;import fj.usp.tmms.repository.SystemUserRepository;import fj.usp.tmms.web.RegistrationForm;
import org.junit.jupiter.api.Test;import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;import static org.mockito.Mockito.*;
class UserRegistrationServiceTest{
 @Test void registrationAlwaysCreatesPublicUser(){SystemUserRepository repo=mock(SystemUserRepository.class);PasswordEncoder enc=mock(PasswordEncoder.class);when(enc.encode(anyString())).thenReturn("hash");when(repo.save(any())).thenAnswer(i->i.getArgument(0));RegistrationForm f=new RegistrationForm();f.setFullName("New User");f.setEmail("new@example.com");f.setPassword("Password1!");SystemUser u=new UserRegistrationService(repo,enc).registerPublic(f);assertEquals(Role.PUBLIC,u.getRole());}
}
