package com.example.demo.service.Repository;

import com.example.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*

This is where connection of database and current project happens

 */
@Repository
public interface UserRepository extends JpaRepository<Users, String> {

}
