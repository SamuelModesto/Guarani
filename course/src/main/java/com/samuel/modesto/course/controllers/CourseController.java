package com.samuel.modesto.course.controllers;

import com.samuel.modesto.course.dtos.CourseDto;
import com.samuel.modesto.course.models.Course;
import com.samuel.modesto.course.services.CourseService;
import com.samuel.modesto.course.specifications.SpecificationTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDto courseDto) {
        var course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        course.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        course.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        course.setUserInstrutor(courseDto.getUserInstructor());
        courseService.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId) {
        Optional<Course> course = courseService.findById(courseId);
        if (!course.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        courseService.delete(course.get());
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable(value = "courseId") UUID courseId,
                                               @RequestBody @Valid CourseDto courseDto) {
        Optional<Course> course = courseService.findById(courseId);
        if (!course.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        var courseModel = course.get();
        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseModel));
    }

    @GetMapping
    public ResponseEntity<Page<Course>> getAllCourses(SpecificationTemplate.CourseSpec spec,
                                                      @PageableDefault(page = 0, size = 10, sort = "courseId",
                                                              direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll(spec, pageable));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getOneCourse(@PathVariable(value = "courseId") UUID courseId) {
        Optional<Course> course = courseService.findById(courseId);
        if (!course.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(course.get());
    }
}
