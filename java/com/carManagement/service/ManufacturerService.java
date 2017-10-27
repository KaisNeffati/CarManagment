package com.carManagement.service;

import com.carManagement.domainobject.models.ManufacturerDO;
import com.carManagement.exception.EntityNotFoundException;

public interface ManufacturerService {
    ManufacturerDO find(long manufactureId) throws EntityNotFoundException;
}
