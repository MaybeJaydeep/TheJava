package com.example.demo.service.UserImplement;

import com.example.demo.Entity.Users;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.UserRepository;
import com.example.demo.service.UserServices;

/*

This is where implementation of the services happens

 */


@Service
public class UserImplement implements UserServices {

    private final UserRepository userRepository;

    public UserImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findById(username).orElse(null);
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
    @Override
    public Users createUser(Users user) {
        if (userRepository.existsById(user.getUsername())) {
            throw new RuntimeException("User already exists");
        }
        return userRepository.save(user);
    }

    // UPDATE
    public Users updateUserName(Users user,String username) {
        if (!userRepository.existsById(user.getUsername())) {
            throw new RuntimeException("User not found");
        }
        user.setUsername(username);
        return userRepository.save(user);
    }

    // DELETE
    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }
}

