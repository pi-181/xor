package com.demkom58.xor;

import com.demkom58.xor.entity.Course;
import com.demkom58.xor.entity.Lecture;
import com.demkom58.xor.repository.LectureRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase
@ContextConfiguration(classes=XorApplication.class)
public class LectureTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LectureRepository lectureRepository;

    private Course createCourse() {
        Course course = new Course(
                "name",
                "descr"
        );

        entityManager.persist(course);
        entityManager.flush();

        return course;
    }

    private Lecture create() {
        Lecture source = new Lecture(
                "con                  ten                   t!!",
                "descr",
                "url",
                createCourse()
        );

        entityManager.persist(source);
        entityManager.flush();
        return source;
    }

    @Test
    public void isIdValid() {
        final Lecture source = create();
        final Lecture deployed = lectureRepository.findLectureById(source.getId());

        Assertions.assertThat(deployed.getId()).isNotNull();
        Assertions.assertThat(deployed.getId()).isGreaterThan(-1);
    }

    @Test
    public void isDescriptionValid() {
        final Lecture source = create();
        final Lecture deployed = lectureRepository.findLectureById(source.getId());

        Assertions.assertThat(
                deployed.getDescription() == null
                        || deployed.getDescription().length() < 3000
        ).isTrue();
    }
}
