package com.example.petapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PetDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String animalType;

    @NotBlank
    private String breed;

    @Min(0)
    private int age;

    @NotBlank
    @Size(min = 8, max = 8)
    private String householdEircode;

    // Getters and setters
}
