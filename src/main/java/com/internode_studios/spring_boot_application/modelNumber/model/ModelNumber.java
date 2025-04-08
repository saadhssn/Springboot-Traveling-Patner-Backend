package com.internode_studios.spring_boot_application.modelNumber.model;

import jakarta.persistence.*;

@Entity
@Table(name = "model_number")
public class ModelNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long vehicleTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}
