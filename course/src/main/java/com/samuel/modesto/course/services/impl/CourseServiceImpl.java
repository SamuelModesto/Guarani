package com.samuel.modesto.course.services.impl;

import com.samuel.modesto.course.repositories.CourseRepository;
import com.samuel.modesto.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
}
