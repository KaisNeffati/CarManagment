package com.carManagement.domainobject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.carManagement.domainobject.DO;
import com.carManagement.domainvalue.ManufacturerName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.carManagement.util.ResourceUtils.generateUniqueId;

@Entity
@Table(
        name = "manufacturer",
        uniqueConstraints = @UniqueConstraint(name = "manufacturer_name", columnNames = {"manufacturer_name"})
)
public class ManufacturerDO extends DO {

    @Id
    @Column(name = "manufacturer_id")
    private Long manufacturerId = generateUniqueId();

    /**
     * collection of cars
     */
    @JsonIgnore
    @OneToMany(mappedBy = "manufacturerDO", fetch = FetchType.LAZY)
    private Collection<CarDO> cars = new ArrayList<>();

    @Column(name = "manufacturer_name", nullable = false, unique = true)
    private ManufacturerName manufacturerName;

    public ManufacturerDO() {
    }

    public ManufacturerDO(ManufacturerName manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public String toString() {
        return "ManufacturerDO{" +
                "manufacturerId=" + manufacturerId +
                ", manufacturerName=" + manufacturerName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManufacturerDO that = (ManufacturerDO) o;

        return manufacturerName == that.manufacturerName;

    }

    @Override
    public int hashCode() {
        return manufacturerName != null ? manufacturerName.hashCode() : 0;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public ManufacturerName getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(ManufacturerName manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}