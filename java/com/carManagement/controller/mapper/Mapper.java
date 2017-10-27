package com.carManagement.controller.mapper;

import com.carManagement.datatransferobject.DTO;
import com.carManagement.domainobject.DO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<T extends DO, D extends DTO> {

    T makeDO(D dTO);

    D makeDTO(T dO);

    T updateDO(T dO, D dTO);


    default List makeDTOList(final Collection<T> dO) {
        return dO.stream()
                .map(this::makeDTO)
                .collect(Collectors.toList());
    }

    default List makeDOList(final Collection<D> dTO) {
        return dTO.stream()
                .map(this::makeDO)
                .collect(Collectors.toList());
    }

}