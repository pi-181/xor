package com.demkom58.xor.repository;

import com.demkom58.xor.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Message findMessageById(Long id);
}
