package com.example.petmanagement.mappers;

import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.entities.Pet;

public class PetMapper {
    public static PetDTO toDto(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setAnimalType(pet.getAnimalType());
        dto.setBreed(pet.getBreed());
        dto.setAge(pet.getAge());
        dto.setHouseholdEircode(pet.getHousehold().getEircode());
        return dto;
    }

    public static Pet toEntity(PetDTO dto) {
        Pet pet = new Pet();
        pet.setId(dto.getId());
        pet.setName(dto.getName());
        pet.setAnimalType(dto.getAnimalType());
        pet.setBreed(dto.getBreed());
        pet.setAge(dto.getAge());
        return pet;
    }
}