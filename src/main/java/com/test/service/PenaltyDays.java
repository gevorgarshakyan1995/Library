package com.test.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
public class PenaltyDays {
    @Scheduled(fixedRate = 36000000L)
    public void PenaltyDays() {

    }
}
