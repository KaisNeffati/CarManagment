package com.carManagement.service.impl;

import com.carManagement.dataaccessobject.CarRepository;
import com.carManagement.dataaccessobject.ManufacturerRepository;
import com.carManagement.domainobject.models.CarDO;
import com.carManagement.domainobject.models.ManufacturerDO;
import com.carManagement.domainobject.requests.CarSearchRequest;
import com.carManagement.exception.ConstraintsViolationException;
import com.carManagement.exception.EntityNotFoundException;
import com.carManagement.service.CarService;
import com.carManagement.util.jpa.FilterBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for cars specific things.
 * <p/>
 */
@Service
public class CarServiceProvider extends GenericServiceProvider implements CarService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(CarServiceProvider.class);

    private final CarRepository carRepository;

    private final ManufacturerRepository manufacturerRepository;


    public CarServiceProvider(final CarRepository carRepository, ManufacturerRepository manufacturerRepository) {
        this.carRepository = carRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * Selects a car by id.
     *
     * @param carId id of card
     * @return found a car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(long carId) throws EntityNotFoundException {
        return findChecked(carId, carRepository);
    }


    /**
     * Creates a new car.
     *
     * @return the created car
     * @throws ConstraintsViolationException if a car already exists with the given License plate, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException {
        CarDO car;
        try {
            List<CarDO> carDOList = carRepository.findByLicensePlate(carDO.getLicensePlate());
            if (carDOList.size() > 0) {
                throw new EntityExistsException("Entity already exit");
            }
            car = carRepository.save(setManufacturer(carDO));
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }

    private CarDO setManufacturer(CarDO carDO) {
        ManufacturerDO manufacturerDO = carDO.getManufacturerDO();
        List<ManufacturerDO> manufacturerList = manufacturerRepository.findByManufacturerName(manufacturerDO.getManufacturerName());
        if (manufacturerList.size() < 1) {
            manufacturerRepository.save(manufacturerDO);
        } else {
            manufacturerDO.setManufacturerId(manufacturerList.get(0).getManufacturerId());
        }
        return carDO;
    }

    /**
     * Deletes an existing car by id.
     *
     * @param carId id of car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(long carId) throws EntityNotFoundException {
        CarDO carDO = find(carId);
        carDO.setDeleted(true);
        carDO.setDateDeleted(ZonedDateTime.now());
        carRepository.save(carDO);
    }

    /**
     * Update an existing car by plate.
     *
     * @param carDO car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public CarDO update(final CarDO carDO) throws EntityNotFoundException {
        CarDO car;
        List<CarDO> carDOList;
        carDOList = carRepository.findByLicensePlate(carDO.getLicensePlate());
        if (carDOList.size() < 1) {
            LOG.warn("Car with the following {} not found", carDO);
            throw new EntityNotFoundException("Car not found");
        }
        car = carDOList.get(0);
        car.setSeatCount(carDO.getSeatCount());
        return carRepository.save(car);
    }

    @Override
    public Collection<CarDO> search(CarSearchRequest request) {
        return searchAll(getSearchFilter(request));
    }

    private FilterBuilder getSearchFilter(CarSearchRequest request) {
        return filter -> {
            if (request.getCarId() != 0L) {
                filter.addEquals("carId", request.getCarId());
            }
            if (request.getSeatCount() != 0) {
                filter.addEquals("seatCount", request.getSeatCount());
            }
            if (request.getLicensePlate() != null) {
                filter.addEquals("licensePlate", request.getLicensePlate());
            }
            if (request.getMyRating() != 0) {
                filter.addEquals("myRating.rating", request.getMyRating());
            }
            if (request.getManufacturerId() != 0) {
                filter.addEquals("ManufacturerDO.manufacturerId", request.getManufacturerId());
            }
            if (request.getEngineType() != null) {
                filter.addEquals("engineType", request.getEngineType());
            }
        };
    }

    @Override
    protected Class getDomainClass() {
        return CarDO.class;
    }

}