package com.carManagement.controller.mapper.impl;

import com.carManagement.controller.mapper.Mapper;
import com.carManagement.datatransferobject.dtos.CarDTO;
import com.carManagement.datatransferobject.dtos.CarDTO.CarDTOBuilder;
import com.carManagement.domainobject.models.CarDO;
import com.carManagement.domainobject.models.ManufacturerDO;
import org.springframework.stereotype.Component;

import static com.carManagement.datatransferobject.dtos.CarDTO.newBuilder;

@Component
public class CarMapper implements Mapper<CarDO, CarDTO> {

    @Override
    public CarDO makeDO(final CarDTO carDTO) {
        return new CarDO(carDTO.getLicensePlate(), carDTO.getSeatCount(), carDTO.getConvertible(), carDTO.getEngineType(), carDTO.getMyRating(), new ManufacturerDO(carDTO.getManufacturerName()));
    }

    @Override
    public CarDTO makeDTO(final CarDO carDO) {
        CarDTOBuilder carDTOBuilder = newBuilder()
                .setId(carDO.getCarId())
                .setManufacturerName(carDO.getManufacturerDO().getManufacturerName())
                .setLicensePlate(carDO.getLicensePlate())
                .setSeatCount(carDO.getSeatCount())
                .setEngineType(carDO.getEngineType())
                .setMyRating(carDO.getMyRating())
                .setConvertible(carDO.getConvertible());

        return carDTOBuilder.createCarDTO();
    }

    @Override
    public CarDO updateDO(final CarDO dO, final CarDTO dTO) {
        dO.setSeatCount(dTO.getSeatCount());
        dO.setLicensePlate(dTO.getLicensePlate());
        return dO;
    }
}