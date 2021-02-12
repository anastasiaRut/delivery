package com.egor.delivery.service.impl;

import com.egor.delivery.model.User;
import com.egor.delivery.repository.UserRepository;
import com.egor.delivery.service.RoleService;
import com.egor.delivery.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final RoleService roleService;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    /**
     * @see UserService#findAll()
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * @see UserService#findById(Long)
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    /**
     * @see UserService#save(User)
     */
    @Override
    public User save(User user) {
        validate(user.getId() != null, "");
        validate(userRepository.existsByUsername(user.getUsername()), "");
        return saveAndFlush(user);
    }

    /**
     * @see UserService#update(User)
     */
    @Override
    public User update(User user) {
        final Long id = user.getId();
        validate(id == null, "");
        final User duplicateUser = userRepository.findByUsername(user.getUsername());
        final boolean isDuplicateExists = duplicateUser != null && !Objects.equals(duplicateUser.getId(), id);
        validate(isDuplicateExists, "");
        findById(id);
        return saveAndFlush(user);
    }

    /**
     * @see UserService#delete(User)
     */
    @Override
    public void delete(User user) {
        final Long id = user.getId();
        validate(id == null, "");
        findById(id);
        userRepository.delete(user);
    }

    /**
     * @see UserService#getOne(Long)
     */
    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    /**
     * @see UserService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    /**
     * @see UserService#findByUsername(String)
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    private User saveAndFlush(User user) {
        validate(user.getRole() == null || user.getRole().getId() == null, "");
        user.setRole(roleService.findById(user.getRole().getId()));
        return userRepository.saveAndFlush(user);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

}
