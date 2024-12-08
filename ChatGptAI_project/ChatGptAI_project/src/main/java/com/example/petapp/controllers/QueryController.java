package com.example.petapp.controllers;

import com.example.petapp.entities.Household;
import com.example.petapp.entities.Pet;
import com.example.petapp.services.HouseholdService;
import com.example.petapp.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;
import org.springframework.graphql.data.method.annotation.QueryMapping;

@Controller
public class QueryController {

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private PetService petService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @QueryMapping
    public List<Pet> getAllPetsByAnimalType(@Argument String animalType) {
        return petService.getPetsByAnimalType(animalType);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @QueryMapping
    public Household getHousehold(@Argument String eircode) {
        return householdService.getHouseholdByEircodeWithPets(eircode);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @QueryMapping
    public Pet getPet(@Argument Long id) {
        return petService.getPetById(id);
    }

    @Secured("ROLE_ADMIN")
    @QueryMapping
    public Statistics getStatistics() {
        long emptyHouses = householdService.countEmptyHouses();
        long fullHouses = householdService.countFullHouses();
        double averagePetAge = petService.getAllPets().stream().mapToInt(Pet::getAge).average().orElse(0);
        int oldestPetAge = petService.getAllPets().stream().mapToInt(Pet::getAge).max().orElse(0);

        return new Statistics(emptyHouses, fullHouses, averagePetAge, oldestPetAge);
    }
}
