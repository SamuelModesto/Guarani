package com.samuel.modesto.course.services.impl;

import com.samuel.modesto.course.models.Course;
import com.samuel.modesto.course.models.Lesson;
import com.samuel.modesto.course.models.Module;
import com.samuel.modesto.course.repositories.CourseRepository;
import com.samuel.modesto.course.repositories.LessonRepository;
import com.samuel.modesto.course.repositories.ModuleRepository;
import com.samuel.modesto.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public void delete(Course course) {
        List<Module> modules = moduleRepository.findAllModulesIntoCourse(course.getCourseId());
        if(!modules.isEmpty()){
            for(Module module : modules){
                List<Lesson> lessons =
            }
        }
    }
}
