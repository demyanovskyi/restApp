package com.demyanovsky.repository;

import com.demyanovsky.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Repository
@Transactional
public interface UserDataRepository extends CrudRepository<User, UUID> {
    }

