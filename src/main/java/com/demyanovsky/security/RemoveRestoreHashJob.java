package com.demyanovsky.security;


import com.demyanovsky.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Configuration
@EnableAsync
@EnableScheduling
public class RemoveRestoreHashJob {
    private static final Logger logger = LoggerFactory.getLogger(RemoveRestoreHashJob.class);
    private static final int HOUR = 3600000;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Scheduled(fixedDelay = HOUR)
    public void removeObsoleteHashes() {
        logger.info("Starting checking validity period");
        OffsetDateTime dateTime = OffsetDateTime.now(ZoneOffset.UTC);
        userRepository.removeObsoleteHashes(null, dateTime);
    }
}