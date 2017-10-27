package com.carManagement.service;

import com.carManagement.domainobject.models.DriverDO;
import com.carManagement.domainobject.requests.DriverSearchRequest;
import com.carManagement.domainvalue.OnlineStatus;
import com.carManagement.exception.CarAlreadyInUseException;
import com.carManagement.exception.ConstraintsViolationException;
import com.carManagement.exception.EntityNotFoundException;

import java.util.Collection;
import java.util.List;

public interface DriverService {

    DriverDO find(long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    DriverDO setCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    DriverDO unsetCar(long driverId) throws EntityNotFoundException;

    Collection<DriverDO> search(DriverSearchRequest request);
}
