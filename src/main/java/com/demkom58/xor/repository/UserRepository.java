package com.demkom58.xor.repository;

import com.demkom58.xor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByLogin(String login);
}
