package com.example.petapp.repositories;

import com.example.petapp.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByAnimalTypeIgnoreCase(String animalType);

    @Query("SELECT p FROM Pet p WHERE p.breed = :breed ORDER BY p.age")
    List<Pet> findByBreedOrderByAge(String breed);

    @Query("SELECT p.name, p.animalType, p.breed FROM Pet p")
    List<Object[]> findNameAndBreed();
}
