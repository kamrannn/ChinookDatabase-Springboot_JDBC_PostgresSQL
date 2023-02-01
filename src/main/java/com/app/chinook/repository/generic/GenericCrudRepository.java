package com.app.chinook.repository.generic;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Generic crud repository.
 *
 * @param <T> the type parameter
 */
@Repository
public interface GenericCrudRepository<T> {

    /**
     * Insert a new record in the database.
     *
     * @param var1 the var 1
     * @return the int
     */
    Integer insertNewRecord(T var1);

    /**
     * Get a record from the database against a specific id
     *
     * @param id the var 1
     * @return the t
     */
    T getById(int id);

    /**
     * Find all records from a database.
     *
     * @return the list
     */
    List<T> findAll();


    /**
     * Update a specific record in the database.
     *
     * @param var1 the var 1
     * @return the int
     */
    Integer update(T var1);

}
