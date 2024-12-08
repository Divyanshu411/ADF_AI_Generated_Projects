package com.example.petapp.controllers;

import com.example.petapp.dto.HouseholdDTO;
import com.example.petapp.entities.Household;
import com.example.petapp.services.HouseholdService;
import com.example.petapp.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
public class MutationController {

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private PetService petService;

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public Household createHousehold(@Valid @Argument HouseholdDTO input) {
        Household household = new Household(
                input.getEircode(),
                input.getNumberOfOccupants(),
                input.getMaxNumberOfOccupants(),
                input.getOwnerOccupied(),
                null
        );
        return householdService.createHousehold(household);
    }

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public boolean deleteHouseholdByEircode(@Argument String eircode) {
        try {
            householdService.deleteHouseholdByEircode(eircode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public boolean deletePetById(@Argument Long id) {
        try {
            petService.deletePetById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
