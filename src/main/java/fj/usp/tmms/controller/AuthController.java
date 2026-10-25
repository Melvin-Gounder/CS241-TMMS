package fj.usp.tmms.controller;

import fj.usp.tmms.service.UserRegistrationService;
import fj.usp.tmms.web.RegistrationForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserRegistrationService registration;
    public AuthController(UserRegistrationService registration){this.registration=registration;}
    @GetMapping("/") public String home(){return "redirect:/traffic";}
    @GetMapping("/login") public String login(){return "login";}
    @GetMapping("/register") public String register(Model model){model.addAttribute("registrationForm",new RegistrationForm());return "register";}
    @PostMapping("/register") public String register(@Valid @ModelAttribute RegistrationForm registrationForm,BindingResult result,Model model){if(result.hasErrors())return "register";try{registration.registerPublic(registrationForm);return "redirect:/login?registered";}catch(IllegalArgumentException ex){model.addAttribute("registrationError",ex.getMessage());return "register";}}
}
