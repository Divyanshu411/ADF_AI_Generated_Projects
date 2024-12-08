package com.example.petapp.controllers;

import com.example.petapp.dto.PetDTO;
import com.example.petapp.entities.Pet;
import com.example.petapp.services.PetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank; // added later
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPet(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PostMapping
    public Pet createPet(@RequestBody @Valid PetDTO petDTO) {
        Pet pet = new Pet(null, petDTO.getName(), petDTO.getAnimalType(), petDTO.getBreed(), petDTO.getAge(), null);
        return petService.createPet(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
    }

    @PatchMapping("/{id}/name")
    public Pet updatePetName(@PathVariable Long id, @RequestParam @NotBlank String newName) {
        Pet pet = petService.getPetById(id);
        pet.setName(newName);
        return petService.updatePet(id, pet);
    }
}
