package com.test.service;

import com.test.exception.NotFoundException;

import java.security.Principal;

public interface EmployService {
    void Buy(Principal principal, int id);

    void rent(Principal principal, int id);

    void ResevedBook(Principal principal, int id);

    void ResevedBookRent(Principal principal, int id, String token);

    void ResevedBookBuy(Principal principal, int id, String token);

    void ReturBook(int id) throws NotFoundException;
}
