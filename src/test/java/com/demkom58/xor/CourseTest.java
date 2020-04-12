package com.demkom58.xor;

import com.demkom58.xor.entity.Course;
import com.demkom58.xor.entity.Lecture;
import com.demkom58.xor.entity.Message;
import com.demkom58.xor.repository.CourseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.StringUtils;

@DataJpaTest
@AutoConfigureTestDatabase
@ContextConfiguration(classes=XorApplication.class)
public class CourseTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    private Course create() {
        Course course = new Course(
                "name",
                "descr"
        );

        entityManager.persist(course);
        entityManager.flush();

        return course;
    }

    @Test
    public void isIdValid() {
        final Course source = create();
        final Course deployed = courseRepository.findCourseById(source.getId());

        Assertions.assertThat(deployed.getId()).isNotNull();
        Assertions.assertThat(deployed.getId()).isGreaterThan(-1);
    }

    @Test
    public void isContentValid() {
        final Course source = create();
        final Course deployed = courseRepository.findCourseById(source.getId());

        Assertions.assertThat(
                deployed.getDescription() == null || deployed.getDescription().length() < 3000
        ).isTrue();
    }

}
