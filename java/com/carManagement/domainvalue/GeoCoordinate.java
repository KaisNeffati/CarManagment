package com.carManagement.domainvalue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// point was removed due to Serialization issues
@Embeddable
public class GeoCoordinate {

    private static final int MAX_LATITUDE = 90;
    private static final int MIN_LATITUDE = -90;
    private static final int MAX_LONGITUDE = 180;
    private static final int MIN_LONGITUDE = -180;
    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public GeoCoordinate() {
    }

    /**
     * @param latitude  - y coordinate
     * @param longitude - x coordinate
     */
    public GeoCoordinate(final double latitude, final double longitude) {
        CheckCoordinate(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private void CheckCoordinate(double latitude, double longitude) {
        Preconditions.checkArgument(latitude >= MIN_LATITUDE, "latitude is lower than min_latitude: " + MIN_LATITUDE);
        Preconditions.checkArgument(latitude <= MAX_LATITUDE, "latitude is higher than max_latitude: " + MAX_LATITUDE);
        Preconditions.checkArgument(longitude >= MIN_LONGITUDE, "longitude is lower than min_longitude: " + MIN_LONGITUDE);
        Preconditions.checkArgument(longitude <= MAX_LONGITUDE, "longitude is higher than max_longitude: " + MAX_LONGITUDE);
    }

    @Override
    public String toString() {
        return "GeoCoordinate{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoCoordinate that = (GeoCoordinate) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        return Double.compare(that.longitude, longitude) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @JsonProperty
    public double getLatitude() {
        return latitude;
    }


    @JsonProperty
    public double getLongitude() {
        return longitude;
    }

}
