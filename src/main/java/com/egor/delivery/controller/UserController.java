package com.egor.delivery.controller;

import com.egor.delivery.dto.request.UserRequestDto;
import com.egor.delivery.dto.response.UserResponseDto;
import com.egor.delivery.model.Role;
import com.egor.delivery.model.User;
import com.egor.delivery.service.UserService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Mapper mapper;

    private final UserService userService;


    public UserController(Mapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    /**
     * Gets all Users
     *
     * @return ResponseEntity<List<UserResponseDto>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponseDto>> getAll() {
        final List<User> users = userService.findAll();
        final List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        users.stream()
                .forEach((User) -> userResponseDtoList.add(getUserDto(User)));
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    /**
     * Gets one User
     *
     * @param id -id
     * @return ResponseEntity<UserResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponseDto> getOne(@PathVariable Long id) {
        final UserResponseDto userResponseDto = getUserDto(userService.findById(id));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    /**
     * Saves User
     *
     * @param userRequestDto - userRequestDto
     * @return ResponseEntity<UserResponseDto>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(null);
        final UserResponseDto userResponseDto = getUserDto(userService.save(getUser(userRequestDto)));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    /**
     * Updates User
     *
     * @param userRequestDto - userRequestDto
     * @return ResponseEntity<UserResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, userRequestDto.getId())) {
            throw new RuntimeException("");
        }
        final UserResponseDto userResponseDto = getUserDto(userService.update(getUser(userRequestDto)));
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    /**
     * Deletes User
     *
     * @param id - id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

    private User getUser(UserRequestDto userRequestDto) {
        final User user = mapper.map(userRequestDto, User.class);
        final Role role = new Role();
        role.setId(userRequestDto.getRoleId());
        user.setRole(role);
        return user;
    }

    private UserResponseDto getUserDto(User user) {
        final UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
        userResponseDto.setRoleId(user.getRole().getId());
        return userResponseDto;
    }

}
