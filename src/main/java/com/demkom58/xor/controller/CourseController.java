package com.demkom58.xor.controller;

import com.demkom58.xor.entity.Course;
import com.demkom58.xor.entity.User;
import com.demkom58.xor.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "courses";
    }

    @GetMapping("/course/{openedCourse}")
    public String courses(@PathVariable Course openedCourse, Model model) {
        model.addAttribute("course", openedCourse);
        return "course";
    }

    @PostMapping("/courses/search")
    public String search(@RequestParam String courseName, Model model) {
        if (courseName == null || courseName.isEmpty())
            return "redirect:/courses";

        model.addAttribute("courses", courseRepository.findByCourseName(courseName));
        return "courses";
    }

    @GetMapping("/courses/addCourse")
    public String addCourse(@AuthenticationPrincipal User user, Model model) {
        return "courses_add";
    }

    @PostMapping("/courses/addCourse")
    public String addCourse(@AuthenticationPrincipal User user,
                            @RequestParam String courseName,
                            @RequestParam String description,
                            Model model) {
        Course course = new Course(courseName, description);
        courseRepository.save(course);

        return "redirect:/course/" + course.getId();
    }

    @GetMapping("/courses/editor")
    public String courseEditor(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "courses_editor";
    }

    @GetMapping("/courses/editor/{openedCourse}")
    public String courseEditor(@PathVariable Course openedCourse, Model model) {
        model.addAttribute("course", openedCourse);
        return "courses_editor";
    }

    @PostMapping("/courses/editor/{openedCourse}")
    public String courseEditor(@PathVariable Course openedCourse,
                               @RequestParam String courseName,
                               @RequestParam String description) {
        openedCourse.setCourseName(courseName);
        openedCourse.setDescription(description);
        courseRepository.save(openedCourse);
        return "redirect:/course/" + openedCourse.getId();
    }

}
