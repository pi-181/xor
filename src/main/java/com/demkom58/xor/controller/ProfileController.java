package com.demkom58.xor.controller;

import com.demkom58.xor.entity.Post;
import com.demkom58.xor.entity.User;
import com.demkom58.xor.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/profile/{user}")
    public String profile(@AuthenticationPrincipal User viewer,
                          @PathVariable User user,
                          Model model) {
        model.addAttribute("ownerViewer", user.getId().equals(viewer.getId()));
        model.addAttribute("user", viewer);
        model.addAttribute("pageUser", user);
        return "profile";
    }

    @PostMapping("/profile/{user}")
    public String comment(@AuthenticationPrincipal User viewer,
                          @PathVariable User user,
                          Post post,
                          Model model) {
        post.setSender(viewer);
        post.setWallOwner(user);

        postRepository.save(post);
        return profile(viewer, user, model);
    }
}
