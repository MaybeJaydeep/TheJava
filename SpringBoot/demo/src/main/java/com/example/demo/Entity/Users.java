package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "UserDetails")
public class Users {
    @Id
    private String username;
    private String password;
    @Column(nullable = true)
    private String email;

    public Users(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Users() {}
}
