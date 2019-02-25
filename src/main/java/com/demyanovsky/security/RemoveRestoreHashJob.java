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

@Configuration
@EnableAsync
@EnableScheduling

public class RemoveRestoreHashJob {
    private static final Logger logger = LoggerFactory.getLogger(RemoveRestoreHashJob.class);
    private static final int HOUR = 3600000;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedDelay = HOUR)
    public void removeObsoleteHashes() {
        logger.info("Starting checking validity period");
        LocalDateTime now = LocalDateTime.now();
        for (User user : userRepository.findAll()) {
            if (user.getValidityPeriod() != null && user.getValidityPeriod().isBefore(now)) {
                user.setValidityPeriod(null);
                user.setRestoreHash(null);
                userRepository.save(user);
            }
        }
    }
}
