package com.demyanovsky.repository;

import com.demyanovsky.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    User findByEmail(String email);

    User findByRestoreHash(String restoreHash);

    @Modifying
    @Query(nativeQuery = true, value = "update users set restore_hash = ?1,validity_period = null  where validity_period < ?2")
    void removeObsoleteHashes(String hash, OffsetDateTime now);
}

