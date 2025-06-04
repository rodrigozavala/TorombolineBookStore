package com.torombolinebookstore.authentication.repo;

import java.util.Optional;

public interface CustomCrudInterface<T,ID> {

    long count();
    void delete(T entity);
    void deleteAll(Iterable<? extends T> entities);
    void deleteAllById(Iterable<? extends ID> ids);
    void deleteById(ID id);//Deletes the entity with the given id.
    boolean existsById(ID id);//Returns whether an entity with the given id exists.
    Iterable<T> findAll();//Returns all instances of the type.
    Iterable<T> findAllById(Iterable<ID> ids);//Returns all instances of the type T with the given IDs.
    Optional<T> findById(ID id);//Retrieves an entity by its id.
    <S extends T> S save(S entity);//Saves a given entity.
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);//Saves all given entities.



}
