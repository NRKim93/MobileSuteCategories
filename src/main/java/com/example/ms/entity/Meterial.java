package com.example.ms.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Meterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int materialId;

    @Column(nullable = false)
    private String materialName;

    private String materialType;
    private double unitPrice;

    @ManyToMany(mappedBy = "materials")
    private List<MobileSuit> mobileSuits = new ArrayList<>();

    public int getMaterialId() {
        return materialId;
    }

    public Meterial() {
    }

    public Meterial(int materialId, String materialName, String materialType, double unitPrice, List<MobileSuit> mobileSuits) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialType = materialType;
        this.unitPrice = unitPrice;
        this.mobileSuits = mobileSuits;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<MobileSuit> getMobileSuits() {
        return mobileSuits;
    }

    public void setMobileSuits(List<MobileSuit> mobileSuits) {
        this.mobileSuits = mobileSuits;
    }
}
