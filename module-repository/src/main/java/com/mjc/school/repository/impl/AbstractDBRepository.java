package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public abstract class AbstractDBRepository<T extends BaseEntity<K>, K> implements BaseRepository<T, K> {
    private final Class<T> entityClass;
    private final Class<K> idClass;
    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractDBRepository() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
        idClass = (Class<K>) type.getActualTypeArguments()[1];
    }

    abstract void updatedEntityFields(T entity, T updatedEntity);

    @Override
    public List<T> readAll() {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM "
                + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }


    //http://host:port/books?page=1&size=20&sort_by=title::asc
    public List<T> readAll(int page, int size, String sortBy){
        String[] order = sortBy.split("::");
        String field = order[0];
        String direction = null;
        if(order.length > 1){
            direction = order[1];
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> entity = query.from(entityClass);

        if(direction != null && direction.equalsIgnoreCase("desc")){
            query.select(entity).orderBy(cb.desc(entity.get(field)));
        }else {
            query.select(entity).orderBy(cb.asc(entity.get(field)));
        }
        return entityManager.createQuery(query).
                setFirstResult(size*(page-1)).
                setMaxResults(size)
                .getResultList();

    }

    @Override
    public Optional<T> readById(K id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
       return readById(entity.getId()).map(existingEntity ->{
           updatedEntityFields(existingEntity, entity);
           T updated = entityManager.merge(existingEntity);
           entityManager.flush();
           return updated;
       }).orElse(null);
    }

    @Override
    public boolean deleteById(K id) {
        if(readById(id).isPresent()){
            T entity = entityManager.find(entityClass, id);
            entityManager.remove(entity);
            return !existById(id);
        }
        return false;
    }

    @Override
    public boolean existById(K id) {
        return readById(id).isPresent();
    }

    @Override
    public T getReference(K id) {
        return entityManager.getReference(this.entityClass, id);
    }
}
