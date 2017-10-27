package com.carManagement.service.impl;

import com.carManagement.dataaccessobject.ManufacturerRepository;
import com.carManagement.domainobject.models.ManufacturerDO;
import com.carManagement.exception.EntityNotFoundException;
import com.carManagement.service.ManufacturerService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceProvider implements ManufacturerService {


    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(ManufacturerServiceProvider.class);

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceProvider(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public ManufacturerDO find(long manufactureId) throws EntityNotFoundException {
        return manufacturerRepository.findOne(manufactureId);
    }
}
