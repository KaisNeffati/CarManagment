package com.carManagement.service;

import com.carManagement.domainobject.models.CarDO;
import com.carManagement.domainobject.requests.CarSearchRequest;
import com.carManagement.exception.ConstraintsViolationException;
import com.carManagement.exception.EntityNotFoundException;

import java.util.Collection;

public interface CarService {

    CarDO find(long carId) throws EntityNotFoundException;

    CarDO create(CarDO CarDO) throws ConstraintsViolationException;

    void delete(long carId) throws EntityNotFoundException;

    CarDO update(CarDO carDO) throws EntityNotFoundException;

    Collection<CarDO> search(CarSearchRequest request);
}
