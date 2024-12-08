package com.example.petapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Household {
    @Id
    private String eircode;

    @Column(nullable = false)
    private int numberOfOccupants;

    @Column(nullable = false)
    private int maxNumberOfOccupants;

    @Column(nullable = false)
    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;
}
