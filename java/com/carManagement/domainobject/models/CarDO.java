package com.carManagement.domainobject.models;

import com.carManagement.domainobject.DO;
import com.carManagement.domainvalue.EngineType;
import com.carManagement.domainvalue.MyRating;
import com.carManagement.util.ResourceUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "car",
        uniqueConstraints = @UniqueConstraint(name = "license_plate", columnNames = {"license_plate"}),
        indexes = {@Index(name = "LicensePlateIdx", columnList = "license_plate")}
)
public class CarDO extends DO {

    private static final long serialVersionUID = -5136167645317983527L;

    @Id
    @Column(name = "car_id")
    private Long carId = ResourceUtils.generateUniqueId();

    @Column(name = "license_plate", nullable = false, unique = true)
    @NotNull(message = "License place can not be null!")
    private String licensePlate;

    @Column(name = "seatCount", nullable = false)
    @NotNull(message = "Seat count can not be null!")
    private int seatCount;

    @Column(nullable = false)
    private Boolean convertible;

    @Embedded
    private MyRating myRating;

    @ManyToOne(fetch = FetchType.EAGER)
    private ManufacturerDO manufacturerDO;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "carDO")
    private DriverDO driver;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type", nullable = false)
    private EngineType engineType;

    public CarDO() {
    }

    public CarDO(String licensePlate, int seatCount, Boolean convertible, EngineType engineType, MyRating myRating, ManufacturerDO manufacturerDO) {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.engineType = engineType;
        this.myRating = myRating;
        this.manufacturerDO = manufacturerDO;
    }

    public DriverDO getDriver() {
        return driver;
    }

    public void setDriver(DriverDO driver) {
        this.driver = driver;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public MyRating getMyRating() {
        return myRating;
    }

    public void setMyRating(MyRating myRating) {
        this.myRating = myRating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public ManufacturerDO getManufacturerDO() {
        return manufacturerDO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarDO carDO = (CarDO) o;

        return licensePlate != null ? licensePlate.equals(carDO.licensePlate) : carDO.licensePlate == null;
    }

    @Override
    public int hashCode() {
        return licensePlate != null ? licensePlate.hashCode() : 0;
    }
}