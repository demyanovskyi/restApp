package com.demyanovsky.repository;

import com.demyanovsky.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {
    User findByEmail(String email);

    User findByRestoreHash(String restoreHash);

    Page<User> findAll(Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "update users set restore_hash = null ,validity_period = null  where validity_period < ?1")
    void removeObsoleteHashes(OffsetDateTime now);
}

