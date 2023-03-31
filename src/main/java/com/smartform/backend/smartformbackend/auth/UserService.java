package com.smartform.backend.smartformbackend.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.smartform.backend.smartformbackend.auth.ERole;
import com.smartform.backend.smartformbackend.auth.Role;
import com.smartform.backend.smartformbackend.auth.repository.RoleRepository;
import com.smartform.backend.smartformbackend.auth.repository.UserRepository;

@Component
public class UserService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        List<User> userList = userRepository.findAll();

        if (userList.size() == 0) {
            userList.add(new User("admin","admin@gmail.com","12345678",null));
            try {
                userRepository.saveAll(userList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}