package com.example.petmanagement.mappers;

import com.example.petmanagement.dto.HouseholdDTO;
import com.example.petmanagement.entities.Household;

public class HouseholdMapper {
    public static HouseholdDTO toDto(Household household) {
        HouseholdDTO dto = new HouseholdDTO();
        dto.setEircode(household.getEircode());
        dto.setNumberOfOccupants(household.getNumberOfOccupants());
        dto.setMaxNumberOfOccupants(household.getMaxNumberOfOccupants());
        dto.setOwnerOccupied(household.isOwnerOccupied());
        return dto;
    }

    public static Household toEntity(HouseholdDTO dto) {
        Household household = new Household();
        household.setEircode(dto.getEircode());
        household.setNumberOfOccupants(dto.getNumberOfOccupants());
        household.setMaxNumberOfOccupants(dto.getMaxNumberOfOccupants());
        household.setOwnerOccupied(dto.getOwnerOccupied());
        return household;
    }
}