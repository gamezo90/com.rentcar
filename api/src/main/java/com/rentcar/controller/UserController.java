package com.rentcar.controller;

import com.rentcar.controller.mappers.UserMapper;
import com.rentcar.controller.requests.UserRequests.UserUpdateRequest;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.User;
import com.rentcar.service.RoleService;
import com.rentcar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
@Tag(name = "User controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    private final RoleService roleService;


    @Operation(summary = "Find all users", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping("/findAllUsers")
    public ResponseEntity<Object> findAllUsers() {

        return new ResponseEntity<>(Collections.singletonMap("result", userService.findAll()), HttpStatus.OK);
    }

    @Operation(summary = "Find user by id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping("/findUserById/{id}")
        public ResponseEntity<Object> findUserById(@RequestParam("userId") Long userId) {
        UserResponse userResponse = userMapper.toResponse(userService.findById(userId));
        return new ResponseEntity<>(Collections.singletonMap("user", userResponse), HttpStatus.OK);
    }

    @Operation(summary = "Find user by login", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping("/findUserByLogin")
    public ResponseEntity<Object> findUserByLogin(@RequestParam("user_login") String login) {

        return new ResponseEntity<>(Collections.singletonMap("result", userService.findByLogin(login)), HttpStatus.OK);
    }

    @Operation(summary = "Soft delete user by login", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PatchMapping("/softDeleteUserByLogin/{login}")
    public ResponseEntity<Object> softDeleteUserByLogin(@PathVariable("login") String login) {

        User user = userService.softDelete(login);

        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }

    @Operation(summary = "Ban delete user by login", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PatchMapping("/banUserByLogin/{login}")
    public ResponseEntity<Object> banUserByLogin(@PathVariable("login") String login) {

        User user = userService.banByLogin(login);

        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }

    @Operation(summary = "Update user by login", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "#principal.getName() == authentication.name")
    @PutMapping(value = "/updateUser/{login}")
    public ResponseEntity<Object> updateUser(Principal principal, @Valid @RequestBody UserUpdateRequest request) {
        User updatedUser = userMapper.convertUpdateRequest(request, userService.findByLogin(principal.getName()));
        userService.checkUserLoginAndEmailForNotExistInDB(updatedUser);
        UserResponse userResponse = userMapper.toResponse(userService.update(updatedUser));
        return new ResponseEntity<>(Collections.singletonMap("user", userResponse), HttpStatus.OK);
    }

    @Operation(summary = "Add role to user by login", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PatchMapping("/addRoleToUserByLogin/{login}")
    public ResponseEntity<Object> addRoleToUserByLogin(@PathVariable("login")String login, String role) {
        User user = userService.addRoleToUser(userService.findByLogin(login), roleService.findRoleByName(role));
        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }

    @Operation(summary = "Remove user role", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PatchMapping("/removeUserRole/{login}")
    public ResponseEntity<Object> removeUserRole(@PathVariable("login")String login, String role) {
        User user = userService.removeUserRole(userService.findByLogin(login), roleService.findRoleByName(role));
        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }
}
