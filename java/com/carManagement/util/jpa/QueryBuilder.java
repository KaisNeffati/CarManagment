package com.carManagement.util.jpa;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public abstract class QueryBuilder<C, V> {

    protected final CriteriaBuilder criteriaBuilder;

    protected final CriteriaQuery<V> query;

    protected final Root<C> root;

    private final List<Predicate> predicates = new ArrayList<>();
    
    public QueryBuilder(final Class<C> clazz, final CriteriaBuilder criteriaBuilder, final CriteriaQuery<V> query) {
        this(criteriaBuilder, query, query.from(clazz));
    }

    public QueryBuilder(final CriteriaBuilder criteriaBuilder, final CriteriaQuery<V> query, final Root<C> root) {
        this.criteriaBuilder = criteriaBuilder;
        this.query = query;
        this.root = root;
    }

    protected abstract CriteriaQuery<V> getSelect();

    public void addEquals(final String field, final Object value) {
        predicates.add(criteriaBuilder.equal(getField(field), value));
    }

    public void addLike(final String field, final String pattern) {
        predicates.add(criteriaBuilder.like(getField(field), pattern));
    }


    @SuppressWarnings("unchecked")
    protected <TField> Path<TField> getField(final String fieldName) {
        Path<?> field = root;
        for (final String fieldPath : fieldName.split("\\.")) {
            field = field.get(fieldPath);
        }

        return (Path<TField>) field;
    }

    public CriteriaQuery<V> build() {
        return getSelect().where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
    }

}
