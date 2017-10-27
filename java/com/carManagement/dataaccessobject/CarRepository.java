package com.carManagement.dataaccessobject;

import com.carManagement.domainobject.models.CarDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long> {
    List<CarDO> findByLicensePlate(String licensePlate);
}
