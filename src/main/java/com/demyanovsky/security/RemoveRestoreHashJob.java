package com.demyanovsky.security;


import com.demyanovsky.domain.User;
import com.demyanovsky.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAsync
@EnableScheduling
public class RemoveRestoreHashJob {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(RemoveRestoreHashJob.class);
    private static final int HOUR = 3600000;

    @Scheduled(fixedDelay = HOUR)
    public void qwe() {
        logger.info("Starting checking validity period");
        LocalDateTime now = LocalDateTime.now();
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        for (User user : users) {
            if (user.getValidityPeriod() != null && user.getValidityPeriod().isBefore(now)) {
                user.setValidityPeriod(null);
                user.setRestoreHash(null);
                userRepository.save(user);
            }
        }
    }
}
