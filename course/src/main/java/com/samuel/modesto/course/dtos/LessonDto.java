package com.samuel.modesto.course.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LessonDto {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String videoUrl;

}
