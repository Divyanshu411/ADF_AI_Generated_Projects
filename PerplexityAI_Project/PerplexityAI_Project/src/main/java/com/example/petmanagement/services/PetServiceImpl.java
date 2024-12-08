package com.example.petmanagement.services;

import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.entities.Household;
import com.example.petmanagement.entities.Pet;
import com.example.petmanagement.mappers.PetMapper;
import com.example.petmanagement.repositories.HouseholdRepository;
import com.example.petmanagement.repositories.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public PetDTO createPet(PetDTO petDTO) {
        Pet pet = PetMapper.toEntity(petDTO);
        Household household = householdRepository.findById(petDTO.getHouseholdEircode())
                .orElseThrow(() -> new EntityNotFoundException("Household not found"));
        pet.setHousehold(household);
        Pet savedPet = petRepository.save(pet);
        return PetMapper.toDto(savedPet);
    }

    @Override
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream()
                .map(PetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
        return PetMapper.toDto(pet);
    }

    @Override
    public PetDTO updatePet(Long id, PetDTO petDTO) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
        pet.setName(petDTO.getName());
        pet.setAnimalType(petDTO.getAnimalType());
        pet.setBreed(petDTO.getBreed());
        pet.setAge(petDTO.getAge());
        Pet updatedPet = petRepository.save(pet);
        return PetMapper.toDto(updatedPet);
    }

    @Override
    public PetDTO updatePetName(Long id, String newName) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
        pet.setName(newName);
        Pet updatedPet = petRepository.save(pet);
        return PetMapper.toDto(updatedPet);
    }

    @Override
    public void deletePetById(Long id) {
        if (!petRepository.existsById(id)) {
            throw new EntityNotFoundException("Pet not found");
        }
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        List<Pet> petsToDelete = petRepository.findByNameIgnoreCase(name);
        petRepository.deleteAll(petsToDelete);
    }

    @Override
    public List<PetDTO> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalTypeIgnoreCase(animalType).stream()
                .map(PetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> findPetsByBreed(String breed) {
        return petRepository.findByBreedIgnoreCaseOrderByAgeAsc(breed).stream()
                .map(PetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, String>> getNameAndBreed() {
        return petRepository.findNameAnimalTypeAndBreed();
    }

    @Override
    public Map<String, Number> getPetStatistics() {
        Map<String, Number> statistics = new HashMap<>();
        statistics.put("averageAge", petRepository.findAverageAge());
        statistics.put("oldestAge", petRepository.findOldestAge());
        statistics.put("totalCount", petRepository.count());
        return statistics;
    }
}