package com.example.demo.scheduler;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UserRepository;
import com.example.demo.service.emailService.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;




@Component
public class BirthdayScheduler {
    private static final Logger log = LoggerFactory.getLogger(BirthdayScheduler.class);
    private final UserRepository userRepository;
    private final EmailService emailService;

    public BirthdayScheduler(UserRepository userRepository,
                             EmailService   emailService) {
        this.userRepository = userRepository;
        this.emailService   = emailService;
    }

    @Scheduled(cron = "0 0 8 * * ?") // Every day at 08:00 AM
    public void sendBirthdayEmails() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day   = today.getDayOfMonth();

        List<Users> birthdayUsers =
                userRepository.findByBirthDate(month, day);


        birthdayUsers.forEach(user -> {
            try {
                // Pick ONE approach below (or toggle by config/feature flag)

                emailService.sendBirthdayEmail_InlineHtml((Users) user);   // Approach 1


            } catch (Exception e) {
                // Log but don't crash the entire batch
                log.error("Failed to send birthday email {}", e.getMessage());
            }
        });
    }
}
