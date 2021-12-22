package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.*;
import com.test.repository.BookRepository;
import com.test.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddresService addresService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public User getBYEmail(String email) throws NotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getall(Integer no, String sort) throws NotFoundException {
        Integer saze = 10;
        PageRequest pageRequest = PageRequest.of(no, saze, Sort.by(sort));
        Page<User> page = userRepository.findAll(pageRequest);
        return page.getContent();
    }

    @Transactional
    @Override
    public void save(User user) throws NotFoundException {
        Address address = addresService.getAllByNumberAndCityAndStreet(user.getAddress());
        if (address == null) {
            addresService.save(user.getAddress());
        } else {
            user.setAddress(address);
        }
        Status status = Status.valueOf("UNVERIFIED");
        user.setStatus(status);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        try {
            mailSender.sendSimpleMessage(user.getEmail(), "Verifiled" + " " + user.getEmail(), user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void DeleteById(int id) {
        userRepository.deleteById(id);
    }


    @Override
    public User getById(int id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new NotFoundException("not id");
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getAllByName(String name) {
        return userRepository.getAllByName(name);
    }


    @Override
    public void verified(String Email) throws NotFoundException {
        User user = getBYEmail(Email);
        if (user == null) {
            throw new NotFoundException();
        } else if (user.getStatus().equals(Status.UNVERIFIED)) {
            Status status = Status.valueOf("VERIFLED");
            user.setStatus(status);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void ResetPasswordToken(String email) throws NotFoundException {
        User user = getBYEmail(email);
        String token = RandomString.make(10);
        Long timeMillis = System.currentTimeMillis();
        user.setTimeMillis(timeMillis);
        user.setResetPasswordToken(token);
        userRepository.save(user);
        mailSender.tokenSimpleMessage(user.getEmail(), "Reset Password", token);
    }

    @Override
    @Transactional
    public User ResetPassword(String token, String password) throws NotFoundException {
        User user = userRepository.getByResetPasswordToken(token);
        if (user == null) {
            throw new NotFoundException();
        }
        Long timeMillis = System.currentTimeMillis();
        Long time = timeMillis - user.getTimeMillis();
        if (time < 120000) {
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setResetPasswordToken(null);
            user.setTimeMillis(null);
            userRepository.save(user);
        } else {
            user.setResetPasswordToken(null);
            user.setTimeMillis(null);
            throw new NotFoundException();
        }

        return user;
    }

    @Override
    public List<User> getPenaltyDays() {
        return userRepository.getPenaltyDays();
    }

    @Override
    public void getPenaltyDaysDelete(int id) throws NotFoundException {
        User user = getById(id);
        user.setPenaltyDays(null);
        userRepository.save(user);
    }

    @Override
    public void getPenaltyDaysmail(int id) throws NotFoundException {
        User user = getById(id);
        mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "please return the book");
    }
}



