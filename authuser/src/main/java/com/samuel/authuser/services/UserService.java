package com.samuel.authuser.services;

import com.samuel.authuser.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(UUID userId);

    void delete(User user);
}
