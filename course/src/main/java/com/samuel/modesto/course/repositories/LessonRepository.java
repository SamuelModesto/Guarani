package com.samuel.modesto.course.repositories;

import com.samuel.modesto.course.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    @Query(value = "select * from tb_lessons where module_module_id = :moduleId", nativeQuery = true)
    List<Lesson> findAllLessonsIntoModule(@Param("moduleId") UUID moduleId);
}
