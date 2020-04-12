package com.demkom58.xor.repository;

import com.demkom58.xor.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findPostById(Long id);
}
