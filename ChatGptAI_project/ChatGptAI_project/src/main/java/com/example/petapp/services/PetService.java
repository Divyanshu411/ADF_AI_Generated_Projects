package com.example.petapp.services;

import com.example.petapp.entities.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    Pet updatePet(Long id, Pet pet);
    void deletePetById(Long id);
    void deletePetsByName(String name);
    List<Pet> getPetsByAnimalType(String animalType);
    List<Pet> getPetsByBreed(String breed);
    List<Object[]> getNameAndBreed();
    double getAverageAge();
    int getOldestAge();
}
