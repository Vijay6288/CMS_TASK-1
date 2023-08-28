package com.content.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.content.management.model.User;
import com.content.management.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String name, @RequestParam String email,
                         @RequestParam String mobileNumber, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMobileNumber(mobileNumber);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            model.addAttribute("message", "Congratulations! You have logged in successfully.");
        } else {
            model.addAttribute("message", "Username or Password is wrong.");
        }
        return "login";
    }

}
