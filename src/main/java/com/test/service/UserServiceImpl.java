package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.User;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByEmail(String email) throws NotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }
}
