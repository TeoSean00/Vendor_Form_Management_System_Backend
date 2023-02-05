package com.smartform.backend.smartformbackend.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smartform.backend.smartformbackend.auth.ERole;
import com.smartform.backend.smartformbackend.auth.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}
