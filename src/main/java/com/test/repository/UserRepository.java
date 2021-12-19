package com.test.repository;

import com.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from User u where u.email = :email")
    User getByEmail(String email);

    @Query(nativeQuery = true, value = "Select * from user where soundex(name)=soundex(?)") //JDBC motavor voronum maqur sql
    List<User> getAllByName (String name);


    @Query(value = "select u from User u where u.ResetPasswordToken = :token")
    User getByResetPasswordToken (String token);

    @Query(nativeQuery = true ,value = "SELECT * FROM user_library WHERE penalty_days > 0")
    List<User> getPenaltyDays ();


}