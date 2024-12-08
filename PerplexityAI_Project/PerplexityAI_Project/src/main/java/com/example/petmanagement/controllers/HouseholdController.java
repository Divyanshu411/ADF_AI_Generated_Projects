package com.example.petmanagement.controllers;

import com.example.petmanagement.dto.HouseholdDTO;
import com.example.petmanagement.services.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @GetMapping
    public ResponseEntity<List<HouseholdDTO>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<HouseholdDTO> getHouseholdByEircode(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.getHouseholdByEircode(eircode));
    }

    @GetMapping("/{eircode}/with-pets")
    public ResponseEntity<HouseholdDTO> getHouseholdByEircodeWithPets(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.getHouseholdByEircodeWithPets(eircode));
    }

    @PostMapping
    public ResponseEntity<HouseholdDTO> createHousehold(@Valid @RequestBody HouseholdDTO householdDTO) {
        return new ResponseEntity<>(householdService.createHousehold(householdDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{eircode}")
    public ResponseEntity<HouseholdDTO> updateHousehold(@PathVariable String eircode, @Valid @RequestBody HouseholdDTO householdDTO) {
        return ResponseEntity.ok(householdService.updateHousehold(eircode, householdDTO));
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHouseholdByEircode(@PathVariable String eircode) {
        householdService.deleteHouseholdByEircode(eircode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/no-pets")
    public ResponseEntity<List<HouseholdDTO>> findHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping("/owner-occupied")
    public ResponseEntity<List<HouseholdDTO>> findOwnerOccupiedHouseholds() {
        return ResponseEntity.ok(householdService.findOwnerOccupiedHouseholds());
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getHouseholdStatistics() {
        return ResponseEntity.ok(householdService.getHouseholdStatistics());
    }
}