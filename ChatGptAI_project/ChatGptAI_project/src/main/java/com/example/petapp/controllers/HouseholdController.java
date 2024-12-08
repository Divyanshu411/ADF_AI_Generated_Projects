package com.example.petapp.controllers;

import com.example.petapp.dto.HouseholdDTO;
import com.example.petapp.entities.Household;
import com.example.petapp.services.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @GetMapping("/{eircode}")
    public Household getHousehold(@PathVariable String eircode) {
        return householdService.getHouseholdByEircode(eircode);
    }

    @GetMapping("/{eircode}/withPets")
    public Household getHouseholdWithPets(@PathVariable String eircode) {
        return householdService.getHouseholdByEircodeWithPets(eircode);
    }

    @GetMapping("/noPets")
    public List<Household> getHouseholdsWithNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @GetMapping("/ownerOccupied")
    public List<Household> getOwnerOccupiedHouseholds() {
        return householdService.findOwnerOccupiedHouseholds();
    }

    @PostMapping
    public Household createHousehold(@RequestBody @Valid HouseholdDTO householdDTO) {
        Household household = new Household(householdDTO.getEircode(), householdDTO.getNumberOfOccupants(),
                householdDTO.getMaxNumberOfOccupants(), householdDTO.getOwnerOccupied(), null);
        return householdService.createHousehold(household);
    }

    @DeleteMapping("/{eircode}")
    public void deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHouseholdByEircode(eircode);
    }

    @GetMapping("/stats")
    public String getHouseholdStatistics() {
        return "Empty Houses: " + householdService.countEmptyHouses() + ", Full Houses: " + householdService.countFullHouses();
    }

    @PutMapping("/{eircode}")
    public Household updateHousehold(@PathVariable String eircode, @RequestBody @Valid HouseholdDTO householdDTO) {
        Household household = new Household(householdDTO.getEircode(), householdDTO.getNumberOfOccupants(),
                householdDTO.getMaxNumberOfOccupants(), householdDTO.getOwnerOccupied(), null);
        return householdService.updateHousehold(eircode, household);
    }
}
