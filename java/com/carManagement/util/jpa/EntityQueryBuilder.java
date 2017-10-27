package com.carManagement.util.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class EntityQueryBuilder<T> extends QueryBuilder<T, T> {

    public EntityQueryBuilder(final Class<T> clazz, final CriteriaBuilder criteriaBuilder) {
        super(clazz, criteriaBuilder, criteriaBuilder.createQuery(clazz));
    }

    @Override
    protected CriteriaQuery<T> getSelect() {
        return query.select(root);
    }
}
