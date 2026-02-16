package com.example.demo.Repository;

import com.example.demo.Entity.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*

This is where connection of database and current project happens

 */
@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsernameAndPassword(String username, String password);
    Optional<Users> findByEmailAndPassword(String email, String password);
}
