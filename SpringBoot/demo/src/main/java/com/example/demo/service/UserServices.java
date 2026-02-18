package com.example.demo.service;

import com.example.demo.Entity.Users;


/*

This is the contract of the project, which shows only the behaviors or services
 */

public interface UserServices {

    Users findByUsername(String username);

    Users findByEmail(String email);

    Users findByUsernameAndPassword(String username, String password);

    Users findByEmailAndPassword(String email, String password);

    void createUser(Users user);

    Users updateUserName(Users user, String username);

    void deleteUser(Users user,String username);

}

