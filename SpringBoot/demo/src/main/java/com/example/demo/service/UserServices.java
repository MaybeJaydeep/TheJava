package com.example.demo.service;

import com.example.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/*

This is the contract of the project, which shows only the behaviors or services
 */

public interface UserServices{

    public Users findByUsername(String username);
    public Users findByEmail(String email);
    public Users findByUsernameAndPassword(String username, String password);
    public Users findByEmailAndPassword(String email, String password);
    public void saveUser(Users user);
}
