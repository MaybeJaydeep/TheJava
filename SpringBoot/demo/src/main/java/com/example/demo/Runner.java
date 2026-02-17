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
        Users user = new Users();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("badaljaydeep@gmail.com");
        LocalDate date = LocalDate.now();
        user.setBirthDate(date);

        userServices.createUser(user);
        System.out.println(toString(userServices.findByUsername(user.getUsername())));

    }

    private String toString(Users admin) {
        return admin.getUsername();
    }


}
