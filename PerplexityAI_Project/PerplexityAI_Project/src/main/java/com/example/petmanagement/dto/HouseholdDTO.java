package com.example.petmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HouseholdDTO {
    @NotBlank(message = "Eircode is required")
    private String eircode;

    @NotNull(message = "Number of occupants is required")
    @Min(value = 0, message = "Number of occupants must be non-negative")
    private Integer numberOfOccupants;

    @NotNull(message = "Max number of occupants is required")
    @Min(value = 1, message = "Max number of occupants must be at least 1")
    private Integer maxNumberOfOccupants;

    @NotNull(message = "Owner occupied status is required")
    private Boolean ownerOccupied;
}