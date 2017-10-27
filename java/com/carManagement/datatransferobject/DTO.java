package com.carManagement.datatransferobject;

import javax.validation.constraints.NotNull;

public abstract class DTO {

    @NotNull(message = "id can not be null!")
    private long id;

    public DTO() {
    }

    public DTO(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}