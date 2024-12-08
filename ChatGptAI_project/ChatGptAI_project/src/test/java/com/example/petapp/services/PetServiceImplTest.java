package com.example.petapp.services;

import com.example.petapp.entities.Pet;
import com.example.petapp.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceImplTest {

    @InjectMocks
    private PetServiceImpl petService;

    @Mock
    private PetRepository petRepository;

    private Pet pet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pet = Pet.builder()
                .id(1L)
                .name("Buddy")
                .animalType("Dog")
                .breed("Golden Retriever")
                .age(3)
                .build();
    }

    @Test
    void createPet() {
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet createdPet = petService.createPet(pet);

        assertNotNull(createdPet);
        assertEquals("Buddy", createdPet.getName());
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void getAllPets() {
        when(petRepository.findAll()).thenReturn(Arrays.asList(pet));

        List<Pet> pets = petService.getAllPets();

        assertEquals(1, pets.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void getPetById() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Pet foundPet = petService.getPetById(1L);

        assertNotNull(foundPet);
        assertEquals("Buddy", foundPet.getName());
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    void updatePet() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet updatedPet = petService.updatePet(1L, pet);

        assertNotNull(updatedPet);
        assertEquals("Buddy", updatedPet.getName());
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void deletePetById() {
        doNothing().when(petRepository).deleteById(1L);

        petService.deletePetById(1L);

        verify(petRepository, times(1)).deleteById(1L);
    }
}
