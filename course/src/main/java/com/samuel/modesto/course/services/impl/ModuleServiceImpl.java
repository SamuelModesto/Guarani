package com.samuel.modesto.course.services.impl;

import com.samuel.modesto.course.models.Lesson;
import com.samuel.modesto.course.models.Module;
import com.samuel.modesto.course.repositories.LessonRepository;
import com.samuel.modesto.course.repositories.ModuleRepository;
import com.samuel.modesto.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
