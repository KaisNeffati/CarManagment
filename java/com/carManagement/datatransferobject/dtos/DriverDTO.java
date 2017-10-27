package com.carManagement.datatransferobject.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.carManagement.datatransferobject.DTO;
import com.carManagement.domainvalue.GeoCoordinate;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO extends DTO {

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private GeoCoordinate coordinate;

    public DriverDTO() {
    }

    DriverDTO(long id, String username, GeoCoordinate coordinate) {
        super(id);
        this.username = username;
        this.coordinate = coordinate;
    }

    public static DriverDTOBuilder newBuilder() {
        return new DriverDTOBuilder();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public GeoCoordinate getCoordinate() {
        return coordinate;
    }

    public static class DriverDTOBuilder {

        private long id;

        private String username;

        private String password;

        private GeoCoordinate coordinate;

        public DriverDTOBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public DriverDTOBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public DriverDTOBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public DriverDTO createDriverDTO() {
            return new DriverDTO(id, username, coordinate);
        }
    }
}