package com.test.controller;

import com.test.exception.NotFoundException;
import com.test.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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

    @PostMapping("/Reseved-Buy")
    public void ResevedBookBuy(Principal principal, @RequestParam("id") int id, @RequestParam("token") String token) {
        employService.ResevedBookBuy(principal, id, token);
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @PutMapping
    public void ReturBook(@RequestParam("id") int id) throws NotFoundException {
        employService.ReturBook(id);

    }
}
