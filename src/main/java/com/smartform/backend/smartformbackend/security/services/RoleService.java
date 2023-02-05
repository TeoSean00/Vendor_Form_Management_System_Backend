package com.smartform.backend.smartformbackend.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.smartform.backend.smartformbackend.auth.ERole;
import com.smartform.backend.smartformbackend.auth.Role;
import com.smartform.backend.smartformbackend.auth.repository.RoleRepository;

@Component
public class RoleService implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        List<Role> roleList = new ArrayList<Role>();
        roleList.add(new Role(ERole.ROLE_USER));
        roleList.add(new Role(ERole.ROLE_ADMIN));
        roleList.add(new Role(ERole.ROLE_MODERATOR));

        try {
            roleRepository.saveAll(roleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
