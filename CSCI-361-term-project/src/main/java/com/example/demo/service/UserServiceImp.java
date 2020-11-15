package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.data.Category;
import com.example.demo.data.CategoryRepository;
import com.example.demo.data.Guest;
import com.example.demo.data.Role;
import com.example.demo.data.RoleRepository;
import com.example.demo.data.GuestRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    GuestRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void saveUser(Guest user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRoleName("guest");
        user.setRole(userRole);
        Category userCategory = categoryRepository.findByCategory("basic");
        user.setCategory(userCategory);
        userRepository.save(user);
    }

    @Override
    public boolean isUserAlreadyPresent(Guest user) {
        // Try to implement this method, as assignment.
        return false;
    }

}
