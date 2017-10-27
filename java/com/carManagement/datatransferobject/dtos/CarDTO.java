package com.carManagement.datatransferobject.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.carManagement.datatransferobject.DTO;
import com.carManagement.domainvalue.EngineType;
import com.carManagement.domainvalue.ManufacturerName;
import com.carManagement.domainvalue.MyRating;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO extends DTO {

    @NotNull(message = "License plate can not be null!")
    private String licensePlate;

    @NotNull(message = "Seat count can not be null!")
    private int seatCount;

    @NotNull(message = "Engine type can not be null!")
    private EngineType engineType;

    @NotNull(message = "Convertible can not be null!")
    private Boolean convertible;

    private MyRating myRating;

    @NotNull(message = "Manufacturer can not be null!")
    private ManufacturerName manufacturerName;

    public CarDTO() {
    }

    public CarDTO(long id, String licensePlate, int seatCount, EngineType engineType, Boolean convertible, final MyRating myRating) {
        super(id);
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.engineType = engineType;
        this.convertible = convertible;
        this.myRating = myRating;
    }

    public static CarDTOBuilder newBuilder() {
        return new CarDTOBuilder();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public MyRating getMyRating() {
        return myRating;
    }

    public ManufacturerName getManufacturerName() {
        return manufacturerName;
    }

    public static class CarDTOBuilder {
        private long id;

        private String licensePlate;

        private int seatCount;

        private EngineType engineType;

        private Boolean convertible;

        private MyRating myRating;

        private ManufacturerName manufacturerName;

        public CarDTOBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public CarDTOBuilder setMyRating(final MyRating myRating) {
            this.myRating = myRating
            ;
            return this;
        }

        public CarDTOBuilder setSeatCount(int seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public CarDTOBuilder setEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarDTOBuilder setConvertible(Boolean convertible) {
            this.convertible = convertible;
            return this;
        }

        public CarDTOBuilder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public CarDTO createCarDTO() {
            return new CarDTO(id, licensePlate, seatCount, engineType, convertible, myRating);
        }

        public CarDTOBuilder setManufacturerName(ManufacturerName manufacturerName) {
            this.manufacturerName = manufacturerName;
            return this;
        }
    }
}