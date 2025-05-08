package com.mehdi.examensarbete_sti2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 8082/login
    @GetMapping("/register") // /api/users/register
    public String showRegisterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register"; // Visa register.html
    }

    @PostMapping("/register") ///api/users/register
    public String registerUser(@ModelAttribute RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userService.registerUser(user);
        return "redirect:/api/users/login";

    }
    @GetMapping("/login")// 8081/api/users/login, 8081/login
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login"; // Visa login.html
    }

    @PostMapping("/login") // /api/users/login
    public String loginUser(@ModelAttribute LoginRequest loginRequest) {
        // Hantera login logik här (kan anpassas till Spring Security)
        return "redirect:/"; // Redirect till startsidan efter inloggning
    }

}