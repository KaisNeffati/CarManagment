package com.carManagement.domainobject.requests;


public class DriverSearchRequest {

    private Long driverId;

    private String username;

    private long carId;

    private String licensePlate;

    @Override
    public String toString() {
        return "DriverSearchRequest{" +
                "driverId=" + driverId +
                ", username='" + username + '\'' +
                ", carId=" + carId +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }

    public long getDriverId() {
        return driverId;
    }

    public String getUsername() {
        return username;
    }

    public long getCarId() {
        return carId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
