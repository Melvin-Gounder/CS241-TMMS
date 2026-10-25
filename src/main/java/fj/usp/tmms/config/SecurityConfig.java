package fj.usp.tmms.config;

import fj.usp.tmms.repository.SystemUserRepository;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
    @Bean UserDetailsService userDetailsService(SystemUserRepository users){return username->{var u=users.findByEmailIgnoreCase(username).orElseThrow(()->new UsernameNotFoundException("User not found"));return new User(u.getEmail(),u.getPasswordHash(),u.isEnabled(),true,true,true,List.of(new SimpleGrantedAuthority("ROLE_"+u.getRole().name())));};}
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(a->a
            .requestMatchers("/","/login","/register","/traffic","/api/public/**","/css/**","/js/**","/error").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/operator/**").hasAnyRole("OPERATOR","ADMIN")
            .requestMatchers("/authority/**").hasAnyRole("AUTHORITY","ADMIN")
            .requestMatchers("/emergency/**").hasAnyRole("EMERGENCY","OPERATOR","ADMIN")
            .anyRequest().authenticated())
            .formLogin(f->f.loginPage("/login").defaultSuccessUrl("/dashboard",true).permitAll())
            .logout(l->l.logoutSuccessUrl("/login?logout"));
        return http.build();
    }
}
