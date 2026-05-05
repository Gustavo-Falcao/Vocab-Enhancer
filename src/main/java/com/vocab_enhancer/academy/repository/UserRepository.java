package com.vocab_enhancer.academy.repository;

import com.vocab_enhancer.academy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
