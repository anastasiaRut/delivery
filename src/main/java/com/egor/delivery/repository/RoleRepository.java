package com.egor.delivery.repository;

import com.egor.delivery.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find Role and fetching Users
     * JPA QL implementation
     *
     * @param nameOfRole - nameOfRole
     * @return Role
     */
    @Query("FROM Role role JOIN FETCH role.users WHERE role.name = :name")
    Role findRoleWithUsers(@Param("name") String nameOfRole);

    /**
     * Find page of All Roles and fetching Users
     * JPA QL implementation
     *
     * @return List<Role>
     */
    @Query("FROM Role role JOIN FETCH role.users")
    List<Role> findAllWithUsers();

    /**
     * Find out by name if this role exists
     *
     * @param roleName - name of type
     * @return boolean
     */
    boolean existsByName(String roleName);

    /**
     * Find Role by name
     * JPA QL implementation
     *
     * @param roleName - name of Role
     * @return Role
     */
    Role findByName(String roleName);

}
