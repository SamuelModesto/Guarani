package com.samuel.modesto.course.controllers;

import com.samuel.modesto.course.dtos.CourseDto;
import com.samuel.modesto.course.dtos.ModuleDto;
import com.samuel.modesto.course.models.Course;
import com.samuel.modesto.course.models.Module;
import com.samuel.modesto.course.services.CourseService;
import com.samuel.modesto.course.services.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    CourseService courseService;

    @PostMapping("/courses/{courseId}/modules")
    public ResponseEntity<Object> saveModule(@PathVariable(value = "courseId") UUID courseId,
                                             @RequestBody @Valid ModuleDto moduleDto) {
        Optional<Course> course = courseService.findById(courseId);
        if (!course.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        var module = new Module();
        BeanUtils.copyProperties(moduleDto, module);
        module.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        module.setCourse(course.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.save(module));
    }

    @DeleteMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId,
                                               @PathVariable(value = "moduleId") UUID moduleId) {
        Optional<Module> module = moduleService.findModuleIntoCourse(courseId, moduleId);
        if (!module.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course");
        }
        moduleService.delete(module.get());
        return ResponseEntity.status(HttpStatus.OK).body("Module deleted");
    }


}
