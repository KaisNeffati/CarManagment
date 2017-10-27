package com.carManagement.controller;

import com.carManagement.controller.mapper.impl.DriverMapper;
import com.carManagement.datatransferobject.dtos.DriverDTO;
import com.carManagement.domainobject.models.DriverDO;
import com.carManagement.domainobject.requests.DriverSearchRequest;
import com.carManagement.domainvalue.OnlineStatus;
import com.carManagement.exception.CarAlreadyInUseException;
import com.carManagement.exception.ConstraintsViolationException;
import com.carManagement.exception.EntityNotFoundException;
import com.carManagement.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
@PreAuthorize("@permissionService.checkApiAccess()")
public class DriverController {

    private final DriverService driverService;

    private final DriverMapper driverMapper;

    @Autowired
    public DriverController(final DriverService driverService, final DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        return driverMapper.makeDTO(driverService.find(driverId));
    }

    // Send empty req => retrieve all data
    @PostMapping("/search")
    public Collection<DriverDO> search(@RequestBody final DriverSearchRequest request) {
        return driverService.search(request);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
        DriverDO driverDO = driverMapper.makeDO(driverDTO);
        return driverMapper.makeDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}/setCar")
    public DriverDO setCar(
            @Valid @PathVariable long driverId, @Valid @RequestBody long carId)
            throws ConstraintsViolationException, EntityNotFoundException, CarAlreadyInUseException {
        return driverService.setCar(driverId, carId);
    }

    @PutMapping("/{driverId}/unsetCar")
    public DriverDO unsetCar(
            @Valid @PathVariable long driverId)
            throws ConstraintsViolationException, EntityNotFoundException {
        return driverService.unsetCar(driverId);
    }

    @PutMapping("/{driverId}")
    public void updateLocation(
            @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
            throws ConstraintsViolationException, EntityNotFoundException {
        driverService.updateLocation(driverId, longitude, latitude);
    }

    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException, EntityNotFoundException {
        return driverMapper.makeDTOList(driverService.find(onlineStatus));
    }
}