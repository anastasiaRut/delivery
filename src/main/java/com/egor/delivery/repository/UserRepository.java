package com.egor.delivery.repository;

import com.egor.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds User by username
     *
     * @param username
     * @return User
     */
    User findByUsername(String username);


    /**
     * Find out by username if this user exists
     *
     * @param username - name of type
     * @return boolean
     */
    boolean existsByUsername(String username);
}
