package com.example.petmanagement.services;

import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.entities.Household;
import com.example.petmanagement.entities.Pet;
import com.example.petmanagement.repositories.HouseholdRepository;
import com.example.petmanagement.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PetServiceTests {

    @Mock
    private PetRepository petRepository;

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePet() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName("TestDog");
        petDTO.setAnimalType("Dog");
        petDTO.setBreed("TestBreed");
        petDTO.setAge(3);
        petDTO.setHouseholdEircode("D02XY45");

        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("TestDog");
        pet.setAnimalType("Dog");
        pet.setBreed("TestBreed");
        pet.setAge(3);

        Household household = new Household();
        household.setEircode("D02XY45");

        when(petRepository.save(any(Pet.class))).thenReturn(pet);
        when(householdRepository.findById("D02XY45")).thenReturn(Optional.of(household));

        PetDTO savedPet = petService.createPet(petDTO);

        assertThat(savedPet).isNotNull();
        assertThat(savedPet.getName()).isEqualTo("TestDog");
        assertThat(savedPet.getAnimalType()).isEqualTo("Dog");
        assertThat(savedPet.getBreed()).isEqualTo("TestBreed");
        assertThat(savedPet.getAge()).isEqualTo(3);
    }

    @Test
    public void testGetPetById() {
        Long petId = 1L;
        Pet pet = new Pet();
        pet.setId(petId);
        pet.setName("TestDog");
        pet.setAnimalType("Dog");
        pet.setBreed("TestBreed");
        pet.setAge(3);

        when(petRepository.findById(petId)).thenReturn(Optional.of(pet));

        PetDTO foundPet = petService.getPetById(petId);

        assertThat(foundPet).isNotNull();
        assertThat(foundPet.getId()).isEqualTo(petId);
        assertThat(foundPet.getName()).isEqualTo("TestDog");
    }

    @Test
    public void testDeletePetById() {
        Long petId = 1L;
        when(petRepository.existsById(petId)).thenReturn(true);

        assertDoesNotThrow(() -> petService.deletePetById(petId));

        verify(petRepository, times(1)).deleteById(petId);
    }
}