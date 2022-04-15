package com.samuel.modesto.course.services;

import com.samuel.modesto.course.models.Module;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

    void delete(Module module);

    Module save(Module module);

    Optional<Module> findModuleIntoCourse(UUID courseId, UUID moduleId);

    List<Module> findAllByCourse(UUID courseId);

    Optional<Module> findById(UUID moduleId);
}
