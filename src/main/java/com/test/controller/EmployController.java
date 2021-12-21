package com.test.controller;

import com.test.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/employ")
public class EmployController {

    @Autowired
    private EmployService employService;

    @PostMapping("/buy")
    public void Buy(Principal principal, @RequestParam("id") int id) { //id Book
        employService.Buy(principal, id);

    }

    @PostMapping("/rent")
    public void rent(Principal principal, @RequestParam("id") int id) { //id Book
        employService.rent(principal, id);
    }

    @PostMapping("/Reseved")
    public void ResevedBook(Principal principal, @RequestParam("id") int id) { //id Book
        employService.ResevedBook(principal, id);
    }

    @PostMapping("/Reseved-rent")
    public void ResevedBookRent(Principal principal, @RequestParam("id") int id, @RequestParam("token") String token) {
        employService.ResevedBookRent(principal, id, token);
    }
}
