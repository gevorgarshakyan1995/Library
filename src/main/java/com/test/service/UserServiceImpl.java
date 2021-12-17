package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Address;
import com.test.model.Status;
import com.test.model.User;
import com.test.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import javax.transaction.Transactional;
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

    @Override
    public User getBYEmail(String email) throws NotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getall() throws NotFoundException {
        return userRepository.findAll();
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
            mailSender.sendSimpleMessage("gevorgarshkyan1995@gmail.com", "Verifiled" + " " + user.getEmail(), user.getEmail());
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
    public void sendemail(String toEmail, String subject, String email) {
        try {
            mailSender.sendSimpleMessage(toEmail, subject, email);
        } catch (
                Exception e
        ) {
            e.printStackTrace();
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
        mailSender.tokenSimpleMessage("gevorgarshkyan1995@gmail.com", "Reset Password", token);
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
}
