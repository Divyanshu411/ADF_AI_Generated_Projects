package com.example.petmanagement.services;

import com.example.petmanagement.dto.HouseholdDTO;
import com.example.petmanagement.entities.Household;
import java.util.List;
import java.util.Map;

public interface HouseholdService {
    HouseholdDTO createHousehold(HouseholdDTO householdDTO);
    List<HouseholdDTO> getAllHouseholds();
    HouseholdDTO getHouseholdByEircode(String eircode);
    HouseholdDTO getHouseholdByEircodeWithPets(String eircode);
    HouseholdDTO updateHousehold(String eircode, HouseholdDTO householdDTO);
    void deleteHouseholdByEircode(String eircode);
    List<HouseholdDTO> findHouseholdsWithNoPets();
    List<HouseholdDTO> findOwnerOccupiedHouseholds();
    Map<String, Long> getHouseholdStatistics();
}