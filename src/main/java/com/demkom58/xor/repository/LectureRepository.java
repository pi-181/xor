package com.demkom58.xor.repository;

import com.demkom58.xor.entity.Lecture;
import org.springframework.data.repository.Repository;

public interface LectureRepository extends Repository<Lecture, Long> {
    Lecture findLectureById(Long id);
}
