package com.samuel.modesto.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samuel.modesto.course.models.Module;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
}
