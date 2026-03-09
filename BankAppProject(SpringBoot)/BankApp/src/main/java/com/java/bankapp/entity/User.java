package com.java.bankapp.entity;

import jakarta.persistence.*;

    @Entity
    public class User {

        @Id
        @GeneratedValue
        private Long id;

        private String username;
        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        @OneToOne
        private Customer customer;
    }
