package com.example.petapp.controllers;

public class Statistics {
    private long emptyHouses;
    private long fullHouses;
    private double averagePetAge;
    private int oldestPetAge;

    public Statistics(long emptyHouses, long fullHouses, double averagePetAge, int oldestPetAge) {
        this.emptyHouses = emptyHouses;
        this.fullHouses = fullHouses;
        this.averagePetAge = averagePetAge;
        this.oldestPetAge = oldestPetAge;
    }

    public long getEmptyHouses() {
        return emptyHouses;
    }

    public long getFullHouses() {
        return fullHouses;
    }

    public double getAveragePetAge() {
        return averagePetAge;
    }

    public int getOldestPetAge() {
        return oldestPetAge;
    }
}
