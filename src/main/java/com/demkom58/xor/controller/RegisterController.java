package com.demkom58.xor.controller;

import com.demkom58.xor.entity.Role;
import com.demkom58.xor.entity.User;
import com.demkom58.xor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user, Model model, @Param("password2") String password2) {
        if (userRepository.findByLogin(user.getUsername()) != null) {
            model.addAttribute("message", "User same login exists!");
            return "register";
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("message", "User same email exists!");
            return "register";
        }

        if (!user.getPassword().equals(password2)) {
            model.addAttribute("message", "Recheck your password!");
            return "register";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
