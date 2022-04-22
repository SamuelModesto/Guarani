package com.samuel.modesto.course.services.impl;

import com.samuel.modesto.course.models.Lesson;
import com.samuel.modesto.course.models.Module;
import com.samuel.modesto.course.repositories.LessonRepository;
import com.samuel.modesto.course.repositories.ModuleRepository;
import com.samuel.modesto.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(Module module) {
        List<Lesson> lessons = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
        if (!lessons.isEmpty()) {
            lessonRepository.deleteAll(lessons);
        }
        moduleRepository.delete(module);
    }

    @Override
    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public Optional<Module> findModuleIntoCourse(UUID courseId, UUID moduleId) {
        return moduleRepository.findModuleIntoCourse(courseId, moduleId);
    }

    @Override
    public List<Module> findAllByCourse(UUID courseId) {
        return moduleRepository.findAllModulesIntoCourse(courseId);
    }

    @Override
    public Optional<Module> findById(UUID moduleId) {
        return moduleRepository.findById(moduleId);
    }

    @Override
    public Page<Module> findAllByCourse(Specification<Module> specification, Pageable pageable) {
        return moduleRepository.findAll(specification, pageable);
    }
}
