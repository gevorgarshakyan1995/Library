package com.test.service;


import com.test.exception.NotFoundException;
import com.test.model.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

public interface UserService {


    List<User> getall() throws NotFoundException;

    void save(User users) throws NotFoundException;

    void DeleteById(int id);

    User getById(int id) throws NotFoundException;

    User getBYEmail(String email) throws NotFoundException;

    List<User> getAllByName(String name);

    void verified(String Email) throws NotFoundException;

    void sendemail(String email, String subject, String text);

    void ResetPasswordToken(String email) throws NotFoundException;

    User ResetPassword(String token, String password) throws NotFoundException;

    List<User> getPenaltyDays();

    void getPenaltyDaysDelete(int id) throws NotFoundException;

    void getPenaltyDaysmail(int id) throws NotFoundException;

    void Buy(Principal principal, int id);

    }
