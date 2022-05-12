package com.samuel.modesto.course.repositories;

import com.samuel.modesto.course.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
