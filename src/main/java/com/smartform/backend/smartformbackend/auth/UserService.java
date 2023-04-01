package com.smartform.backend.smartformbackend.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.smartform.backend.smartformbackend.auth.payload.request.SignupRequest;
import com.smartform.backend.smartformbackend.auth.repository.RoleRepository;
import com.smartform.backend.smartformbackend.auth.repository.UserRepository;

@Component
@Order(2)
@DependsOn("roleService")
public class UserService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        List<User> userList = userRepository.findAll();
        // JSONObject adminObj = new JSONObject(
        // "{\"username\":\"admin\",\"password\":\"12345678\",\"email\":\"admin@gmail.com\",\"roles\":[\"admin\"]}");
        if (userList.size() == 0) {
            User adminUser = new User("admin", "admin@gmail.com", encoder.encode("12345678"), null);
            Set<Role> roles = new HashSet<>();
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            adminUser.setRoles(roles);

            User approverUser = new User("approver", "approver@gmail.com", encoder.encode("87654321"), null);
            Set<Role> approverRoles = new HashSet<>();
            Role approverRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            approverRoles.add(approverRole);
            approverUser.setRoles(approverRoles);
            try {
                userRepository.save(adminUser);
                userRepository.save(approverUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}