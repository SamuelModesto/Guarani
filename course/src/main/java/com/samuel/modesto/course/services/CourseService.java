package com.samuel.modesto.course.services;

import com.samuel.modesto.course.models.Course;

public interface CourseService {

    void delete(Course course);

    Course save(Course course);
}
