package com.carManagement.domainobject.models;

import com.carManagement.domainobject.DO;
import com.carManagement.domainvalue.GeoCoordinate;
import com.carManagement.domainvalue.OnlineStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

import static com.carManagement.util.ResourceUtils.generateUniqueId;

@Entity
@Table(
        name = "driver",
        uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = {"username"})
)
public class DriverDO extends DO {
    private static final long serialVersionUID = -3504296692998637717L;

    @Id
    @Column(name = "driver_id")
    private Long driverId = generateUniqueId();

    @Column(nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Embedded
    private GeoCoordinate coordinate;

    /**
     * Car for this driver
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CarDO carDO;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnlineStatus onlineStatus;


    private DriverDO() {
    }

    public DriverDO(String username, GeoCoordinate coordinate) {
        this.username = username;
        this.coordinate = coordinate;
    }

    public DriverDO(String username, String password) {
        super();
        this.username = username;
        this.coordinate = null;
        this.dateCoordinateUpdated = null;
        this.onlineStatus = OnlineStatus.OFFLINE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverDO driverDO = (DriverDO) o;

        return username != null ? username.equals(driverDO.username) : driverDO.username == null;

    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    public CarDO getCarDO() {
        return carDO;
    }

    public void setCarDO(final CarDO carDO) {
        this.carDO = carDO;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }


    public void setOnlineStatus(OnlineStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }


    public GeoCoordinate getCoordinate() {
        return coordinate;
    }


    public void setCoordinate(GeoCoordinate coordinate) {
        this.coordinate = coordinate;
        this.dateCoordinateUpdated = ZonedDateTime.now();
    }
}