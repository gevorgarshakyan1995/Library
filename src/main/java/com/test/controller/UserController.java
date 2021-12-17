package com.test.controller;

import com.test.exception.NotFoundException;
import com.test.model.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RolesAllowed(value = "ROLE_ADMIN")
    @GetMapping
    List<User> getAll()  throws NotFoundException {
        return userService.getall();
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @GetMapping("{id}")
    User getById(@PathVariable int id) throws NotFoundException {
        return userService.getById(id);
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @GetMapping("/get-by-email")
    User getByEmail(@RequestParam("email") String email) throws NotFoundException {
        return userService.getBYEmail(email);
    }

    @DeleteMapping
    public void DeleteById(@RequestParam("id") int id) {
        userService.DeleteById(id);
    }


    @GetMapping("/get-all-by-name")
    public List<User> getAllByName(@RequestParam("name") String name) {
        return userService.getAllByName(name);
    }

    @PostMapping
    void save(@RequestBody User user) throws NotFoundException {
        userService.save(user);
    }

    @PostMapping("/verify")
    void verified(@RequestParam("email") String email) throws NotFoundException {
        userService.verified(email);
    }

    @PostMapping("/send-email")
    void sendemail(@RequestParam("email") String toEmail, @RequestParam("subject") String subject, @RequestParam("email") String email) {
        userService.sendemail(toEmail, subject, email);
    }

    @PostMapping("/reset-password")
    void ResetPasswordToken(@RequestParam("email") String Email) throws NotFoundException {
        userService.ResetPasswordToken(Email);
    }

    @PutMapping("/reset-password")
    public User ResetPassword(@RequestParam("token") String token, @RequestParam("password") String password) throws NotFoundException {
        return userService.ResetPassword(token, password);
    }

    @RolesAllowed(value = "ROLE_ADMIN") //penaltyDays
    @GetMapping("/get-penalty-Days")
    public User getPenaltyDays(){
        return null;
    }
}