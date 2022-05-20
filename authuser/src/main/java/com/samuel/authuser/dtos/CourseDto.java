package com.samuel.authuser.dtos;

import com.samuel.authuser.enums.CourseLevel;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDto {
    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus courseStatus;
    private UUID userInstructor;
    private CourseLevel courseLevel;
}
