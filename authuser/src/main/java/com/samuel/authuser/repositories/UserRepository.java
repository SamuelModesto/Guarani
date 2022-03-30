package com.samuel.authuser.repositories;

import com.samuel.authuser.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
