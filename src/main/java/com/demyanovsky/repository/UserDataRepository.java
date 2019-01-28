package com.demyanovsky.repository;

import com.demyanovsky.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDataRepository extends CrudRepository<User, UUID>, CustomUserRepository {
    }

