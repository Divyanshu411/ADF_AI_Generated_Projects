package com.example.petapp.services;

import com.example.petapp.entities.Pet;
import com.example.petapp.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @Override
    public Pet updatePet(Long id, Pet pet) {
        Pet existingPet = getPetById(id);
        existingPet.setName(pet.getName());
        existingPet.setAge(pet.getAge());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        petRepository.deleteAll(petRepository.findAll().stream().filter(p -> p.getName().equalsIgnoreCase(name)).toList());
    }

    @Override
    public List<Pet> getPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalTypeIgnoreCase(animalType);
    }

    @Override
    public List<Pet> getPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }

    @Override
    public List<Object[]> getNameAndBreed() {
        return petRepository.findNameAndBreed();
    }

    @Override
    public double getAverageAge() {
        return petRepository.findAll().stream().mapToInt(Pet::getAge).average().orElse(0);
    }

    @Override
    public int getOldestAge() {
        return petRepository.findAll().stream().mapToInt(Pet::getAge).max().orElse(0);
    }
}
