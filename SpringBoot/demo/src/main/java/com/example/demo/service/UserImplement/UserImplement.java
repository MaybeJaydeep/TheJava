package com.example.demo.service.UserImplement;

import com.example.demo.Entity.Users;
import com.example.demo.service.UserServices;
import com.example.demo.service.emailService.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.UserRepository;


import java.time.LocalDate;

/*

This is where implementation of the services happens

 */


@Service
public class UserImplement implements UserServices {
    private static final Logger log = LoggerFactory.getLogger(UserImplement.class);

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserImplement(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public Users findByUsername(String username) {
        return null;
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Users findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .orElse(null);
    }

    @Override
    public Users findByEmailAndPassword(String email, String password) {
        return userRepository
                .findByEmailAndPassword(email, password)
                .orElse(null);
    }

    // CREATE
    public void createUser(Users user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        Users saved = userRepository.save(user);

        if (saved.getBirthDate() != null &&
                saved.getBirthDate().equals(LocalDate.now())) {
            try {
                emailService.sendBirthdayEmail_InlineHtml(saved);
            } catch (Exception e) {
                log.warn("Could not send birthday email on create: {}", e.getMessage());
            }
        }
    }

    // UPDATE
    public Users updateUserName(Users user, String username) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("User not found");
        }
        user.setUsername(username);
        return userRepository.save(user);
    }

    // DELETE
    @Override
    public void deleteUser(Users user, String username) {
        userRepository.deleteById(user.getId());
    }

    

}

