package com.example.petapp.services;

import com.example.petapp.entities.Household;
import com.example.petapp.repositories.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdByEircode(String eircode) {
        return householdRepository.findById(eircode).orElseThrow(() -> new RuntimeException("Household not found"));
    }

    @Override
    public Household getHouseholdByEircodeWithPets(String eircode) {
        return householdRepository.findByEircodeWithPets(eircode);
    }

    @Override
    public Household updateHousehold(String eircode, Household household) {
        Household existingHousehold = getHouseholdByEircode(eircode);
        existingHousehold.setNumberOfOccupants(household.getNumberOfOccupants());
        existingHousehold.setMaxNumberOfOccupants(household.getMaxNumberOfOccupants());
        existingHousehold.setOwnerOccupied(household.isOwnerOccupied());
        return householdRepository.save(existingHousehold);
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) {
        householdRepository.deleteById(eircode);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findOwnerOccupiedHouseholds();
    }

    @Override
    public long countEmptyHouses() {
        return householdRepository.findAll().stream().filter(h -> h.getNumberOfOccupants() == 0).count();
    }

    @Override
    public long countFullHouses() {
        return householdRepository.findAll().stream().filter(h -> h.getNumberOfOccupants() == h.getMaxNumberOfOccupants()).count();
    }
}
