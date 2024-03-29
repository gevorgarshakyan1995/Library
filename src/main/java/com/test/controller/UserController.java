package com.test.controller;

import com.test.exception.NotFoundException;
import com.test.model.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RolesAllowed(value = "ROLE_ADMIN")
    @GetMapping
    List<User> getAll(@RequestParam("no") Integer no, @RequestParam("sort") String sort) throws NotFoundException {
        return userService.getall(no, sort); // no = page nomer
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
    public List<User> getPenaltyDays() {
        return userService.getPenaltyDays();
    }

    @RolesAllowed(value = "ROLE_ADMIN") //penaltyDays
    @PutMapping("/get-penalty-Days-delete")
    public void getPenaltyDaysDelete(@RequestParam("id") int id) throws NotFoundException {
        userService.getPenaltyDaysDelete(id);
    }

    @RolesAllowed(value = "ROLE_ADMIN") //penaltyDays
    @PutMapping("/get-penalty-Days-mail")
    public void getPenaltyDaysmail(@RequestParam("id") int id) throws NotFoundException {
        userService.getPenaltyDaysmail(id);
    }

}