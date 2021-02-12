package com.egor.delivery.service;

import com.egor.delivery.model.Role;

import java.util.List;

public interface RoleService {
    /**
     * Save Role entity in database
     *
     * @param role - role
     * @return
     */
    Role save(Role role);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - Role entity
     */
    void delete(Role entity);

    /**
     * Returns a reference to the Role entity
     *
     * @param id - id
     * @return Role
     */
    Role getOne(Long id);

    /**
     * Updates Role entity
     *
     * @param role - Role
     * @return Role
     */
    Role update(Role role);

    /**
     * Finds Role by id
     *
     * @param id - id
     * @return Role
     */
    Role findById(Long id);

    /**
     * Finds Role by name
     *
     * @param name - name of Role
     * @return Role
     */
    Role findByName(String name);

    /**
     * Finds all Roles
     *
     * @return List<Role>
     */
    List<Role> findAll();

    /**
     * Find Role and fetching Roles
     * JPA QL implementation
     *
     * @param nameOfRole - nameOfRole
     * @return Role
     */
    Role findRoleWithUsers(String nameOfRole);

    /**
     * Find page of All Roles and fetching Roles
     * JPA QL implementation
     *
     * @return List<Role>
     */
    List<Role> findAllWithUsers();
}
