package com.example.demo.service.UserImplement;

import com.example.demo.Entity.Users;
import org.springframework.stereotype.Service;
import com.example.demo.service.Repository.UserRepository;
import com.example.demo.service.UserServices;

/*

This is where implementation of the services happens

 */

@Service
public class UserImplement implements UserServices {

    private final UserRepository userRepository;

    UserImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users findByUsername(String username) {

        return userRepository.findById(username).orElse(null);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findById(email).orElse(null);
    }

    @Override
    public Users findByUsernameAndPassword(String username, String password) {
        return null;
    }


    @Override
    public Users findByEmailAndPassword(String email, String password) {
        return null;
    }

    public void saveUser(Users user) {
        this.userRepository.save(user);
    }

}
