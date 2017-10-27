package com.carManagement.service.impl;

import com.carManagement.exception.EntityNotFoundException;
import com.carManagement.util.jpa.EntityQueryBuilder;
import com.carManagement.util.jpa.FilterBuilder;
import com.carManagement.util.jpa.QueryBuilder;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;

public abstract class GenericServiceProvider<T> {

    // Entity Manager method with dynamic Query builder
    @PersistenceContext
    private EntityManager entityManager;

    // Repository method
    public static <T> T findChecked(long Id, CrudRepository<T, Long> crudRepository) throws EntityNotFoundException {
        T entity = crudRepository.findOne(Id);
        if (entity == null) {
            throw new EntityNotFoundException("Could not find entity with id: " + Id);
        }
        return entity;
    }

    protected abstract Class<T> getDomainClass();

    protected EntityQueryBuilder<T> createEntityQueryBuilder() {
        return new EntityQueryBuilder<>(getDomainClass(), entityManager.getCriteriaBuilder());
    }

    protected Collection<T> find(final CriteriaQuery<T> criteriaQuery) {
        try {
            final TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (final PersistenceException e) {
            throw new ServiceException("Unable to retrieve data", e);
        }
    }

    @Transactional(readOnly = true)
    protected Collection<T> searchAll(final FilterBuilder predicateBuilder) {
        final QueryBuilder<T, T> builder = createEntityQueryBuilder();
        builder.addEquals("deleted", false);
        predicateBuilder.build(builder);
        return find(builder.build());
    }
}
