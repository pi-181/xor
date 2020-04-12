package com.demkom58.xor.controller;

import com.demkom58.xor.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index(@AuthenticationPrincipal User user,
                        Model model) {
        model.addAttribute("user", user);
        return "index";
    }

}