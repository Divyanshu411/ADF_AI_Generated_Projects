package com.example.petmanagement.repositories;

import com.example.petmanagement.entities.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PetRepositoryTests {

    @Autowired
    private PetRepository petRepository;

    @Test
    public void testFindByAnimalTypeIgnoreCase() {
        List<Pet> dogs = petRepository.findByAnimalTypeIgnoreCase("dog");
        assertThat(dogs).hasSize(3);
        assertThat(dogs).allMatch(pet -> pet.getAnimalType().equalsIgnoreCase("dog"));
    }

    @Test
    public void testFindByBreedIgnoreCaseOrderByAgeAsc() {
        List<Pet> goldenRetrievers = petRepository.findByBreedIgnoreCaseOrderByAgeAsc("Golden Retriever");
        assertThat(goldenRetrievers).hasSize(1);
        assertThat(goldenRetrievers.get(0).getName()).isEqualTo("Buddy");
    }

    @Test
    public void testFindAverageAge() {
        Double averageAge = petRepository.findAverageAge();
        assertThat(averageAge).isNotNull();
        assertThat(averageAge).isGreaterThan(0);
    }

    @Test
    public void testFindOldestAge() {
        Integer oldestAge = petRepository.findOldestAge();
        assertThat(oldestAge).isNotNull();
        assertThat(oldestAge).isGreaterThan(0);
    }
}
