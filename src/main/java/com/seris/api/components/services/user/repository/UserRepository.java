package com.seris.api.components.services.user.repository;

import com.seris.api.entities.user.User;
import com.seris.api.components.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findFirstByUsername(String username);

    boolean existsByUsername(String username);
}
