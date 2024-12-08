package com.example.petmanagement.repositories;

import com.example.petmanagement.entities.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, String> {
    @Query("SELECT h FROM Household h LEFT JOIN FETCH h.pets WHERE h.eircode = :eircode")
    Optional<Household> findByEircodeWithPets(String eircode);

    @Query("SELECT h FROM Household h WHERE h.pets IS EMPTY")
    List<Household> findHouseholdsWithNoPets();

    List<Household> findByOwnerOccupiedTrue();

    @Query("SELECT COUNT(h) FROM Household h WHERE h.numberOfOccupants = 0")
    long countEmptyHouseholds();

    @Query("SELECT COUNT(h) FROM Household h WHERE h.numberOfOccupants = h.maxNumberOfOccupants")
    long countFullHouseholds();
}