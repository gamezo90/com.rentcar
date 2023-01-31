package com.rentcar.controller;

import com.rentcar.repository.RoleRepository;
import com.rentcar.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
@Tag(name = "Roles controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService repository;

    @Operation(summary = "Find all roles", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/findAllRoles")
    public ResponseEntity<Object> findAllRoles() {

        return new ResponseEntity<>(Collections.singletonMap("result", repository.findAll()), HttpStatus.OK);
    }

    @Operation(summary = "Find role by role id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/findRoleById")
    public ResponseEntity<Object> findRoleById(@RequestParam("id") Long roleId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findRoleById(roleId)), HttpStatus.OK);
    }

    @Operation(summary = "Find roles by user id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/findRolesByUserId")
    public ResponseEntity<Object> findRolesByUserId(@RequestParam("id") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findRolesByUserId(userId)), HttpStatus.OK);
    }
}
