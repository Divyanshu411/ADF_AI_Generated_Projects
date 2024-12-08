package com.example.petmanagement.controllers;

import com.example.petmanagement.dto.HouseholdDTO;
import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.services.HouseholdService;
import com.example.petmanagement.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class GraphQLController {

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private PetService petService;

    @QueryMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<HouseholdDTO> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<PetDTO> getPetsByAnimalType(@Argument String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public HouseholdDTO getHousehold(@Argument String eircode) {
        return householdService.getHouseholdByEircode(eircode);
    }

    @QueryMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public PetDTO getPet(@Argument Long id) {
        return petService.getPetById(id);
    }

    @QueryMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Map<String, Number> getPetStatistics() {
        return petService.getPetStatistics();
    }

    @MutationMapping
    @Secured("ROLE_ADMIN")
    public HouseholdDTO createHousehold(@Argument HouseholdDTO input) {
        return householdService.createHousehold(input);
    }

    @MutationMapping
    @Secured("ROLE_ADMIN")
    public boolean deleteHousehold(@Argument String eircode) {
        householdService.deleteHouseholdByEircode(eircode);
        return true;
    }

    @MutationMapping
    @Secured("ROLE_ADMIN")
    public boolean deletePet(@Argument Long id) {
        petService.deletePetById(id);
        return true;
    }
}