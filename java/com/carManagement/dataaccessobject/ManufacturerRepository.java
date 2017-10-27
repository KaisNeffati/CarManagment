package com.carManagement.dataaccessobject;

import com.carManagement.domainobject.models.ManufacturerDO;
import com.carManagement.domainvalue.ManufacturerName;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long> {
    List<ManufacturerDO> findByManufacturerName(ManufacturerName manufacturerName);
}
