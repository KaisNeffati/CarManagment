package com.carManagement.util.jpa;

@FunctionalInterface
public interface FilterBuilder {
    void build(final QueryBuilder<?, ?> filter);
}
