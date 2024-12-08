package com.example.petapp.services;

import com.example.petapp.entities.Household;

import java.util.List;

public interface HouseholdService {
    Household createHousehold(Household household);
    List<Household> getAllHouseholds();
    Household getHouseholdByEircode(String eircode);
    Household getHouseholdByEircodeWithPets(String eircode);
    Household updateHousehold(String eircode, Household household);
    void deleteHouseholdByEircode(String eircode);
    List<Household> findHouseholdsWithNoPets();
    List<Household> findOwnerOccupiedHouseholds();
    long countEmptyHouses();
    long countFullHouses();
}
