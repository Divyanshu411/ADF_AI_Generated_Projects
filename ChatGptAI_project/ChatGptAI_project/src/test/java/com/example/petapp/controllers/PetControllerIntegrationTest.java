package com.example.petapp.controllers;

import com.example.petapp.entities.Household;
import com.example.petapp.entities.Pet;
import com.example.petapp.repositories.HouseholdRepository;
import com.example.petapp.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    private Household testHousehold;

    @BeforeEach
    void setup() {
        petRepository.deleteAll();
        householdRepository.deleteAll();

        testHousehold = householdRepository.save(
                Household.builder()
                        .eircode("E12345")
                        .numberOfOccupants(3)
                        .maxNumberOfOccupants(5)
                        .ownerOccupied(true)
                        .build()
        );
    }

    @Test
    void getAllPets() throws Exception {
        petRepository.save(
                Pet.builder()
                        .name("Buddy")
                        .animalType("Dog")
                        .breed("Beagle")
                        .age(5)
                        .household(testHousehold)
                        .build()
        );

        mockMvc.perform(get("/pets")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Buddy")));
    }

    @Test
    void deletePetById() throws Exception {
        Pet pet = petRepository.save(
                Pet.builder()
                        .name("Rocky")
                        .animalType("Dog")
                        .breed("Boxer")
                        .age(5)
                        .household(testHousehold)
                        .build()
        );

        mockMvc.perform(delete("/pets/" + pet.getId())
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isOk());

        assertFalse(petRepository.findById(pet.getId()).isPresent());
    }

    @Test
    void updatePetName() throws Exception {
        Pet pet = petRepository.save(
                Pet.builder()
                        .name("Charlie")
                        .animalType("Dog")
                        .breed("Bulldog")
                        .age(4)
                        .household(testHousehold)
                        .build()
        );

        mockMvc.perform(patch("/pets/" + pet.getId() + "/name")
                        .param("newName", "Max")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Max")));

        Pet updatedPet = petRepository.findById(pet.getId()).orElseThrow();
        assertEquals("Max", updatedPet.getName());
    }
}
