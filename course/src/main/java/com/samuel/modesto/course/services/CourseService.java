package com.samuel.modesto.course.services;

import com.samuel.modesto.course.models.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {

    void delete(Course course);

    Course save(Course course);

    Optional<Course> findById(UUID id);

    List<Course> findAll();
}
