package com.example.demo.Repository;

import com.example.demo.Entity.Users;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/*

This is where connection of database and current project happens

 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsernameAndPassword(String username, String password);
    Optional<Users> findByEmailAndPassword(String email, String password);
    @Query("SELECT u FROM Users u WHERE " +
            "MONTH(u.birthDate) = :month AND DAY(u.birthDate) = :day")

    List<Users> findByBirthDate(@Param("month") int month,
                               @Param("day")   int day);

}
