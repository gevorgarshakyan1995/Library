package com.test.service;


import com.test.exception.NotFoundException;
import com.test.model.User;

import java.util.List;

public interface UserService {


    public List<User> getall() throws NotFoundException;

    void save(User users) throws NotFoundException;

    public void DeleteById(int id);

    public User getById(int id) throws NotFoundException;

    public User getBYEmail(String email) throws NotFoundException;

    public List<User> getAllByName(String name);

    void verified(String Email) throws NotFoundException;

    public void sendemail(String email, String subject, String text);

    void ResetPasswordToken(String email) throws NotFoundException;

    User ResetPassword(String token, String password) throws NotFoundException;

    }
