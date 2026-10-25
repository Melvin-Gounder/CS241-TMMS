package fj.usp.tmms.service;

import fj.usp.tmms.model.*;
import fj.usp.tmms.repository.SystemUserRepository;
import fj.usp.tmms.web.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final SystemUserRepository users; private final PasswordEncoder encoder;
    public UserRegistrationService(SystemUserRepository users,PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
    public SystemUser registerPublic(RegistrationForm form){
        String email=form.getEmail().trim().toLowerCase();
        if(users.existsByEmailIgnoreCase(email)) throw new IllegalArgumentException("Email is already registered.");
        SystemUser u=new SystemUser(); u.setFullName(form.getFullName().trim()); u.setEmail(email); u.setPasswordHash(encoder.encode(form.getPassword())); u.setRole(Role.PUBLIC); return users.save(u);
    }
}
