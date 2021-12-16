package com.test.controller;

import com.test.exception.NotFoundException;
import com.test.model.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-by-email")
    User getByEmail(@RequestParam("email") String email) throws NotFoundException {
        return userService.getByEmail(email);
    }

}
