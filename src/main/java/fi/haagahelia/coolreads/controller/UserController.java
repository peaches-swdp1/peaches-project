package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.coolreads.dto.RegisterUserDto;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import fi.haagahelia.coolreads.security.UserDetailsServiceImpl;
import fi.haagahelia.coolreads.model.*;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService; 

    @Autowired
    private AppUserRepository userRepository; 

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDto());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("registerUserDto") @Valid RegisterUserDto registerUserDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userRepository.findByUsername(registerUserDto.getUsername()) != null) {
            result.rejectValue("username", "error.user", "Username is already taken");
            return "register";
        }

        AppUser newUser = new AppUser();
        newUser.setUsername(registerUserDto.getUsername());
        newUser.setPasswordHash(new BCryptPasswordEncoder().encode(registerUserDto.getPassword()));
        newUser.setRole(registerUserDto.getRole());

     
        userRepository.save(newUser);

        
        return "redirect:/login";
    }
}
