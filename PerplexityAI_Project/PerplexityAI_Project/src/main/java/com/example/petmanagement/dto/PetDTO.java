package com.example.petmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Animal type is required")
    private String animalType;

    @NotBlank(message = "Breed is required")
    private String breed;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be non-negative")
    private Integer age;

    @NotBlank(message = "Household eircode is required")
    private String householdEircode;
}