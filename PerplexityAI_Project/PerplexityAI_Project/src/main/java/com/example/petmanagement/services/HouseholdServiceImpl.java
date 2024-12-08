package com.example.petmanagement.services;

import com.example.petmanagement.dto.HouseholdDTO;
import com.example.petmanagement.entities.Household;
import com.example.petmanagement.mappers.HouseholdMapper;
import com.example.petmanagement.repositories.HouseholdRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public HouseholdDTO createHousehold(HouseholdDTO householdDTO) {
        Household household = HouseholdMapper.toEntity(householdDTO);
        Household savedHousehold = householdRepository.save(household);
        return HouseholdMapper.toDto(savedHousehold);
    }

    @Override
    public List<HouseholdDTO> getAllHouseholds() {
        return householdRepository.findAll().stream()
                .map(HouseholdMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HouseholdDTO getHouseholdByEircode(String eircode) {
        Household household = householdRepository.findById(eircode)
                .orElseThrow(() -> new EntityNotFoundException("Household not found"));
        return HouseholdMapper.toDto(household);
    }

    @Override
    public HouseholdDTO getHouseholdByEircodeWithPets(String eircode) {
        Household household = householdRepository.findByEircodeWithPets(eircode)
                .orElseThrow(() -> new EntityNotFoundException("Household not found"));
        return HouseholdMapper.toDto(household);
    }

    @Override
    public HouseholdDTO updateHousehold(String eircode, HouseholdDTO householdDTO) {
        Household household = householdRepository.findById(eircode)
                .orElseThrow(() -> new EntityNotFoundException("Household not found"));
        household.setNumberOfOccupants(householdDTO.getNumberOfOccupants());
        household.setMaxNumberOfOccupants(householdDTO.getMaxNumberOfOccupants());
        household.setOwnerOccupied(householdDTO.getOwnerOccupied());
        Household updatedHousehold = householdRepository.save(household);
        return HouseholdMapper.toDto(updatedHousehold);
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) {
        if (!householdRepository.existsById(eircode)) {
            throw new EntityNotFoundException("Household not found");
        }
        householdRepository.deleteById(eircode);
    }

    @Override
    public List<HouseholdDTO> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets().stream()
                .map(HouseholdMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HouseholdDTO> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupiedTrue().stream()
                .map(HouseholdMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getHouseholdStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("emptyHouseholds", householdRepository.countEmptyHouseholds());
        statistics.put("fullHouseholds", householdRepository.countFullHouseholds());
        return statistics;
    }
}