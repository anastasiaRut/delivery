package com.egor.delivery.controller;

import com.egor.delivery.dto.RoleDto;
import com.egor.delivery.model.Role;
import com.egor.delivery.service.RoleService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final Mapper mapper;

    private final RoleService roleService;


    public RoleController(Mapper mapper, RoleService roleService) {
        this.mapper = mapper;
        this.roleService = roleService;
    }

    /**
     * Gets all Roles
     *
     * @return ResponseEntity<List < RoleResponseDto>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RoleDto>> getAll() {
        final List<Role> roles = roleService.findAll();
        final List<RoleDto> roleDtoList = roles.stream()
                .map((Role) -> mapper.map(Role, RoleDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(roleDtoList, HttpStatus.OK);
    }

    /**
     * Gets one Role
     *
     * @param id -id
     * @return ResponseEntity<RoleResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoleDto> getOne(@PathVariable Long id) {
        final RoleDto roleDto = mapper.map(roleService.findById(id), RoleDto.class);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    /**
     * Saves Role
     *
     * @param roleDto - roleRDto
     * @return ResponseEntity<RoleResponseDto>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RoleDto> save(@Valid @RequestBody RoleDto roleDto) {
        roleDto.setId(null);
        final RoleDto responseRoleDto = mapper.map(roleService.save(mapper.map(roleDto, Role.class)), RoleDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    /**
     * Updates Role
     *
     * @param roleDto - roleDto
     * @return ResponseEntity<RoleResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RoleDto> update(@Valid @RequestBody RoleDto roleDto, @PathVariable Long id) {
        if (!Objects.equals(id, roleDto.getId())) {
            throw new RuntimeException("");
        }
        final RoleDto responseRoleDto = mapper.map(roleService.update(mapper.map(roleDto, Role.class)), RoleDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    /**
     * Deletes Role
     *
     * @param id - id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}

