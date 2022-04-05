package com.samuel.modesto.course.repositories;

import com.samuel.modesto.course.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
