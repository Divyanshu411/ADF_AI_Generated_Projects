package com.example.petmanagement.controllers;

import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.entities.Pet;
import com.example.petmanagement.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PostMapping
    public ResponseEntity<PetDTO> createPet(@Valid @RequestBody PetDTO petDTO) {
        return new ResponseEntity<>(petService.createPet(petDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable Long id, @Valid @RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.updatePet(id, petDTO));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<PetDTO> updatePetName(@PathVariable Long id, @RequestParam String newName) {
        return ResponseEntity.ok(petService.updatePetName(id, newName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deletePetsByName(@PathVariable String name) {
        petService.deletePetsByName(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{animalType}")
    public ResponseEntity<List<PetDTO>> findPetsByAnimalType(@PathVariable String animalType) {
        return ResponseEntity.ok(petService.findPetsByAnimalType(animalType));
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity<List<PetDTO>> findPetsByBreed(@PathVariable String breed) {
        return ResponseEntity.ok(petService.findPetsByBreed(breed));
    }

    @GetMapping("/name-and-breed")
    public ResponseEntity<List<Map<String, String>>> getNameAndBreed() {
        return ResponseEntity.ok(petService.getNameAndBreed());
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Number>> getPetStatistics() {
        return ResponseEntity.ok(petService.getPetStatistics());
    }
}