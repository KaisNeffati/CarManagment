package com.carManagement.controller.mapper.impl;

import com.carManagement.controller.mapper.Mapper;
import com.carManagement.datatransferobject.dtos.DriverDTO;
import com.carManagement.domainobject.models.DriverDO;
import com.carManagement.domainvalue.GeoCoordinate;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper implements Mapper<DriverDO, DriverDTO> {

    @Override
    public DriverDO makeDO(final DriverDTO driverDTO) {
        DriverDO driverDO = new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
        GeoCoordinate coordinate = driverDTO.getCoordinate();
        if (coordinate != null) {
            driverDO.setCoordinate(coordinate);
        }
        return driverDO;
    }

    @Override
    public DriverDTO makeDTO(final DriverDO driverDO) {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
                .setId(driverDO.getDriverId())
                .setUsername(driverDO.getUsername())
                .setCoordinate(driverDO.getCoordinate());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null) {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }

    @Override
    public DriverDO updateDO(final DriverDO dO, final DriverDTO dTO) {
        return null;
    }
}