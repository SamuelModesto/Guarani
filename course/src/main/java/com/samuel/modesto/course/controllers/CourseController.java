package com.samuel.modesto.course.controllers;

import com.samuel.modesto.course.dtos.CourseDto;
import com.samuel.modesto.course.models.Course;
import com.samuel.modesto.course.services.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody CourseDto courseDto){
        var course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        course.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        course.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseService.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }
}
