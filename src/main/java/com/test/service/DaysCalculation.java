package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Book;
import com.test.model.Status;
import com.test.model.StatusBook;
import com.test.model.User;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.List;

@Configuration
@EnableScheduling
public class DaysCalculation {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private MailSender mailSender;

    @Scheduled(fixedRate = 36000000L)
    public void PenaltyDays() {
        List<User> user = userService.getPenaltyDays();
        for (User user1 : user) {
            Long timeMillis = System.currentTimeMillis();
            Long time = timeMillis - user1.getPenaltyDaystaem();
            if (time > 86400000) {
                user1.setPenaltyDays(user1.getPenaltyDays() + 1);
                user1.setPenaltyDaystaem(System.currentTimeMillis());
                try {
                    userRepository.save(user1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mailSender.tokenSimpleMessage(user1.getEmail(), "Good Library", "please return the book");
            }
        }

        user = userService.getPenaltyDays();
        for (User user1 : user) {
            if (user1.getPenaltyDays() > 5) {
                mailSender.tokenSimpleMessage(user1.getEmail(), "Good Library", "please return the book");
                userService.DeleteById(user1.getId());
            }
        }
    }

    @Scheduled(fixedRate = 36000L)
    public void ResevedDays() throws NotFoundException {
        List<Book> book = bookService.getAllByStatus(StatusBook.RESEVED);
        for (Book book1 : book) {
            Long timeMillis = System.currentTimeMillis();
            Long timeBook = book1.getStatusTime();
            if (timeBook - timeMillis > 36000000) {
                book1.setStatusTime(null);
                book1.setStatus(StatusBook.LOOSE);
                book1.setResevedBook(null);
                try {
                    bookService.save(book1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

