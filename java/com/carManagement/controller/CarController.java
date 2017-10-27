package com.carManagement.controller;

import com.carManagement.controller.mapper.impl.CarMapper;
import com.carManagement.datatransferobject.dtos.CarDTO;
import com.carManagement.domainobject.models.CarDO;
import com.carManagement.domainobject.requests.CarSearchRequest;
import com.carManagement.exception.ConstraintsViolationException;
import com.carManagement.exception.EntityNotFoundException;
import com.carManagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * All operations for a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
@PreAuthorize("@permissionService.checkApiAccess()")
public class CarController {

    private final CarService carService;

    private final CarMapper carMapper;

    @Autowired
    public CarController(final CarService carService, final CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    // Send empty req => retrieve all data
    @PostMapping("/search")
    public Collection<CarDO> search(@RequestBody final CarSearchRequest request) {
        return carService.search(request);
    }

    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException {
        return carMapper.makeDTO(carService.find(carId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException {
        CarDO carDO = carMapper.makeDO(carDTO);
        return carMapper.makeDTO(carService.create(carDO));
    }

    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
        carService.delete(carId);
    }

    @PutMapping("/{carId}")
    public CarDTO updateCar(
            @Valid @PathVariable long carId, @Valid @RequestBody final CarDTO carDTO)
            throws ConstraintsViolationException, EntityNotFoundException {
        CarDO carDO = carMapper.makeDO(carDTO);
        return carMapper.makeDTO(carService.update(carDO));
    }

}