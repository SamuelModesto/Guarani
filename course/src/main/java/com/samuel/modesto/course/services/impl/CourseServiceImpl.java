package com.samuel.modesto.course.services.impl;

import com.samuel.modesto.course.models.Course;
import com.samuel.modesto.course.models.Lesson;
import com.samuel.modesto.course.models.Module;
import com.samuel.modesto.course.repositories.CourseRepository;
import com.samuel.modesto.course.repositories.LessonRepository;
import com.samuel.modesto.course.repositories.ModuleRepository;
import com.samuel.modesto.course.services.CourseService;
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
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(Course course) {
        List<Module> modules = moduleRepository.findAllModulesIntoCourse(course.getCourseId());
        if (!modules.isEmpty()) {
            for (Module module : modules) {
                List<Lesson> lessons = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
                if (!lessons.isEmpty()) {
                    lessonRepository.deleteAll(lessons);
                }
            }
            moduleRepository.deleteAll(modules);
        }
        courseRepository.delete(course);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(UUID id) {
        return courseRepository.findById(id);
    }

    @Override
    public Page<Course> findAll(Specification<Course> spec, Pageable pageable) {
        return courseRepository.findAll(spec, pageable);
    }


}
