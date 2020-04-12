package com.demkom58.xor.repository;

import com.demkom58.xor.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findCourseById(Long id);
    List<Course> findByCourseName(String courseName);
}
