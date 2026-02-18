package com.example.demo;

import com.example.demo.Entity.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.service.UserServices;

import java.time.LocalDate;

@Component
public class Runner implements CommandLineRunner {

    private final UserServices userServices;

    public Runner(UserServices userServices) {
        this.userServices = userServices;
    }
    @Override
    public void run(String... args) throws Exception {
        LocalDate date;
        Users user = new Users();
        user.setUsername("Jaydeep");
        user.setPassword("Jaydeep");
        user.setEmail("ditipatel160@gmail.com");
        date = LocalDate.now();
        user.setBirthDate(date);

        userServices.createUser(user);

        System.out.println("User created");
    }


}
