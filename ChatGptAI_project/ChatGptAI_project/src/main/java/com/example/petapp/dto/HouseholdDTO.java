package com.example.petapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class HouseholdDTO {
    @NotBlank
    @Size(min = 8, max = 8)
    private String eircode;

    @Min(0)
    private int numberOfOccupants;

    @Min(1)
    private int maxNumberOfOccupants;

    @NotNull
    private Boolean ownerOccupied;

    // Getters and setters
}
