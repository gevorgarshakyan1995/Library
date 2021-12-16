package com.test.service;


import com.test.exception.NotFoundException;
import com.test.model.User;

public interface UserService {

    User getByEmail(String email) throws NotFoundException;

    }
