package com.test.service;

import com.test.model.Book;
import com.test.model.StatusBook;
import com.test.model.User;
import com.test.repository.BookRepository;
import com.test.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public class EmployServiceImpl implements EmployService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;


    @Transactional
    @Override
    public void Buy(Principal principal, int id) { //id Book
        User user = userRepository.getByEmail(principal.getName());
        try {
            Book book = bookService.getById(id);
            if (user.getWallet() >= book.getValue()) {
                if (book.getUser() == null && book.getStatus().equals(StatusBook.LOOSE)) {
                    int wallet = user.getWallet() - book.getValue();
                    user.setWallet(wallet);
                    userRepository.save(user);
                    mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", book.getName() + "buy book");
                    String text = "buy book" + user.getId() + book.getId();
                    mailSender.tokenSimpleMessage("admin@gmail.com", "Good Library", text);
                    bookService.DeleteById(id);
                } else {
                    mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "Book  RESEVED onli 10dasy");
                }
            }
            mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "There is no corresponding amount");
        } catch (Exception e) {
            e.printStackTrace();
            mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "There is no corresponding amount");
        }


    }

    @Transactional
    @Override
    public void rent(Principal principal, int id) {
        User user = userRepository.getByEmail(principal.getName());
        try {
            Book book = bookService.getById(id);
            if (user.getWallet() >= book.getValueRent()) {
                if (book.getUser() == null && book.getStatus().equals(StatusBook.LOOSE)) {
                    int wallet = user.getWallet() - book.getValueRent();
                    user.setWallet(wallet);
                    Long timeMillis = System.currentTimeMillis();
                    user.setPenaltyDaystaem(timeMillis);
                    userRepository.save(user);
                    mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", book.getName() + "rent book");
                    String text = "rent book" + user.getId() + book.getId();
                    mailSender.tokenSimpleMessage("admin@gmail.com", "Good Library", text);
                    book.setUser(user);
                    bookRepository.save(book);
                } else {
                    mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "There is no corresponding amount");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "There is no corresponding amount");
        }
    }

    @Transactional
    @Override
    public void ResevedBook(Principal principal, int id) {
        User user = userRepository.getByEmail(principal.getName());
        try {
            Book book = bookService.getById(id);
            if (book.getUser() == null && book.getStatus().equals(StatusBook.LOOSE)) {
                String s = RandomString.make(10);
                Long time = System.currentTimeMillis();
                mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", book.getName() + "rent reseved" + "key" + s);
                book.setStatus(StatusBook.RESEVED);
                book.setResevedBook(s);
                book.setStatusTime(time);
                bookRepository.save(book);
            } else {
                mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "book Reseved onli 10 days");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "prplems sory");
        }
    }

    @Override
    public void ResevedBookRent(Principal principal, int id, String token) {
        User user = userRepository.getByEmail(principal.getName());
        try {
            Book book = bookService.getAllByResevedBook(token);
            if (user.getWallet() >= book.getValueRent()) {
                int wallet = user.getWallet() - book.getValueRent();
                user.setWallet(wallet);
                Long timeMillis = System.currentTimeMillis();
                user.setPenaltyDaystaem(timeMillis);
                userRepository.save(user);
                mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", book.getName() + "rent book");
                String text = "rent book" + user.getId() + book.getId();
                mailSender.tokenSimpleMessage("admin@gmail.com", "Good Library", text);
                book.setUser(user);
                bookRepository.save(book);
            } else {
                mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "There is no corresponding amount");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mailSender.tokenSimpleMessage(user.getEmail(), "Good Library", "There is no corresponding amount");

        }
    }

}
