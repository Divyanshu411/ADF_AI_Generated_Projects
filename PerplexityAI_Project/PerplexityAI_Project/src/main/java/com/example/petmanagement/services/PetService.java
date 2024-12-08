package com.example.petmanagement.services;

import com.example.petmanagement.dto.PetDTO;
import java.util.List;
import java.util.Map;

public interface PetService {
    PetDTO createPet(PetDTO petDTO);
    List<PetDTO> getAllPets();
    PetDTO getPetById(Long id);
    PetDTO updatePet(Long id, PetDTO petDTO);
    PetDTO updatePetName(Long id, String newName);
    void deletePetById(Long id);
    void deletePetsByName(String name);
    List<PetDTO> findPetsByAnimalType(String animalType);
    List<PetDTO> findPetsByBreed(String breed);
    List<Map<String, String>> getNameAndBreed();
    Map<String, Number> getPetStatistics();
}