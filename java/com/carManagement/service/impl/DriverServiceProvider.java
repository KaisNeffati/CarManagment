package com.carManagement.service.impl;

import com.carManagement.dataaccessobject.CarRepository;
import com.carManagement.dataaccessobject.DriverRepository;
import com.carManagement.domainobject.models.CarDO;
import com.carManagement.domainobject.models.DriverDO;
import com.carManagement.domainobject.requests.DriverSearchRequest;
import com.carManagement.domainvalue.GeoCoordinate;
import com.carManagement.domainvalue.OnlineStatus;
import com.carManagement.exception.CarAlreadyInUseException;
import com.carManagement.exception.ConstraintsViolationException;
import com.carManagement.exception.EntityNotFoundException;
import com.carManagement.service.DriverService;
import com.carManagement.util.jpa.FilterBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DriverServiceProvider extends GenericServiceProvider implements DriverService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DriverServiceProvider.class);

    private final DriverRepository driverRepository;

    private final CarRepository carRepository;


    public DriverServiceProvider(final DriverRepository driverRepository, CarRepository carRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId driver id
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(long driverId) throws EntityNotFoundException {
        return findChecked(driverId, driverRepository);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO driver id
     * @return return driver
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException {
        DriverDO driver;
        try {
            driver = driverRepository.save(driverDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(long driverId) throws EntityNotFoundException {
        DriverDO driverDO = findChecked(driverId, driverRepository);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException {
        DriverDO driverDO = findChecked(driverId, driverRepository);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus) {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    @Override
    public synchronized DriverDO setCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException {
        DriverDO driverDo = findChecked(driverId, driverRepository);
        if (driverDo.getOnlineStatus() == OnlineStatus.OFFLINE) {
            throw new IllegalArgumentException("Could not set car to OFFLINE driver");
        }
        CarDO carDO = carRepository.findOne(carId);
        if (carDO == null) {
            throw new EntityNotFoundException("Could not find car with id: " + carId);
        }
        if (carDO.getDriver() != null) {
            throw new CarAlreadyInUseException("Car already in use");
        }
        driverDo.setCarDO(carDO);
        return driverRepository.save(driverDo);
    }

    @Override
    public DriverDO unsetCar(long driverId) throws EntityNotFoundException {
        DriverDO driverDO = findChecked(driverId, driverRepository);
        driverDO.setCarDO(null);
        return driverRepository.save(driverDO);
    }

    @Override
    public Collection<DriverDO> search(DriverSearchRequest request) {
        return searchAll(getSearchFilter(request));
    }

    private FilterBuilder getSearchFilter(DriverSearchRequest request) {
        return filter -> {
            if (request.getDriverId() != 0) {
                filter.addEquals("driverId", request.getDriverId());
            }
            if (request.getUsername() != null) {
                filter.addEquals("username", request.getUsername());
            }
            if (request.getCarId() != 0L) {
                filter.addEquals("carDO.carId", request.getCarId());
            }
            if (request.getLicensePlate() != null) {
                filter.addEquals("carDO.licensePlate", request.getLicensePlate());
            }
        };
    }

    @Override
    protected Class getDomainClass() {
        return DriverDO.class;
    }
}
