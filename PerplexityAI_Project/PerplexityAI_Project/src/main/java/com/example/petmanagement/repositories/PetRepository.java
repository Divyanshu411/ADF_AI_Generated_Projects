package com.example.petmanagement.repositories;

import com.example.petmanagement.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    void deleteByNameIgnoreCase(String name);

    List<Pet> findByAnimalTypeIgnoreCase(String animalType);

    List<Pet> findByBreedIgnoreCaseOrderByAgeAsc(String breed);

    @Query("SELECT new map(p.name as name, p.animalType as animalType, p.breed as breed) FROM Pet p")
    List<Map<String, String>> findNameAnimalTypeAndBreed();

    @Query("SELECT AVG(p.age) FROM Pet p")
    Double findAverageAge();

    @Query("SELECT MAX(p.age) FROM Pet p")
    Integer findOldestAge();

    List<Pet> findByNameIgnoreCase(String name);
}