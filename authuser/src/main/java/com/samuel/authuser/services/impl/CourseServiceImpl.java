package com.samuel.authuser.services.impl;

import com.samuel.authuser.repositories.CourseRepository;
import com.samuel.authuser.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

}
