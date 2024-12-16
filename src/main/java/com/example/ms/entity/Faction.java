package com.example.ms.entity;

import jakarta.persistence.*;


@Entity
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int factionId;

    @Column(nullable = false)
    private String factionName;

    private String headquartersLocation;

    public Faction() {

    }

    public Faction(int factionId, String factionName, String headquartersLocation) {
        this.factionId = factionId;
        this.factionName = factionName;
        this.headquartersLocation = headquartersLocation;
    }

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getHeadquartersLocation() {
        return headquartersLocation;
    }

    public void setHeadquartersLocation(String headquartersLocation) {
        this.headquartersLocation = headquartersLocation;
    }
}
