package com.smartform.backend.smartformbackend.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smartform.backend.smartformbackend.models.ERole;
import com.smartform.backend.smartformbackend.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}
