package com.egor.delivery.service;

import com.egor.delivery.model.User;

import java.util.List;

public interface UserService {
    /**
     * Save User entity in database
     * @param user - user
     * @return
     */
    User save(User user);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - User entity
     */
    void delete(User entity);

    /**
     * Returns a reference to the User entity
     *
     * @param id - id
     * @return User
     */
    User getOne(Long id);

    /**
     * Updates User entity
     *
     * @param user - User
     * @return User
     */
    User update(User user);

    /**
     * Finds User by id
     *
     * @param id - id
     * @return User
     */
    User findById(Long id);

    /**
     * Finds all Users
     *
     * @return List<User>
     */
    List<User> findAll();

    /**
     * Finds User by username
     *
     * @param username
     * @return User
     */
    User findByUsername(String username);

}
