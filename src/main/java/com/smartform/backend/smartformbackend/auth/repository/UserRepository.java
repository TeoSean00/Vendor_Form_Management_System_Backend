package com.smartform.backend.smartformbackend.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smartform.backend.smartformbackend.auth.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    List<User> findAll();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}