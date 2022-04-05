package com.samuel.modesto.course.services.impl;

import com.samuel.modesto.course.repositories.LessonRepository;
import com.samuel.modesto.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;
}
