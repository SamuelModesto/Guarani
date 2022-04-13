package com.samuel.modesto.course.services;

import com.samuel.modesto.course.models.Module;

import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

    void delete(Module module);

    Module save(Module module);

    Optional<Module> findModuleIntoCourse(UUID courseId, UUID moduleId);
}
