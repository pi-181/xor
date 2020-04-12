package com.demkom58.xor.service;

import com.demkom58.xor.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseService courseService;

    public Course loadCourseById(Long id) {
        return courseService.loadCourseById(id);
    }
}
