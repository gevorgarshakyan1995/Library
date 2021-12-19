package com.test.service;

import com.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.List;

@Configuration
@EnableScheduling
public class PenaltyDays {
    @Autowired
    private UserService userService;

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
            if (time>86400000){
                user1.setPenaltyDays(user1.getPenaltyDays()+1);
                user1.setPenaltyDaystaem(System.currentTimeMillis());
                try{
                    userService.save(user1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                mailSender.tokenSimpleMessage(user1.getEmail(),"Good Library","please return the book");
            }
        }



        user = userService.getPenaltyDays();
        for (User user1 : user) {
            if(user1.getPenaltyDays()>5){
                mailSender.tokenSimpleMessage(user1.getEmail(),"Good Library","please return the book");
                userService.DeleteById(user1.getId());
            };
        }
    }



}

