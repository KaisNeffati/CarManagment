package com.carManagement.domainobject.requests;

import com.carManagement.domainvalue.EngineType;

public class CarSearchRequest {

    private long carId;

    private int seatCount;

    private String licensePlate;

    private int myRating;

    private long manufacturerId;

    private EngineType engineType;

    @Override
    public String toString() {
        return "CarSearchRequest{" +
                "carId=" + carId +
                ", seatCount=" + seatCount +
                ", licensePlate='" + licensePlate + '\'' +
                ", myRating=" + myRating +
                ", manufacturerId=" + manufacturerId +
                ", engineType=" + engineType +
                '}';
    }


    public long getCarId() {
        return carId;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getMyRating() {
        return myRating;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public EngineType getEngineType() {
        return engineType;
    }
}
