package com.rentcar.controller;

import com.rentcar.controller.mappers.UserMapper;
import com.rentcar.controller.requests.UserRequests.UserUpdateRequest;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.User;
import com.rentcar.service.RoleService;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    private final RoleService roleService;


    @GetMapping("/findAllUser")
    public ResponseEntity<Object> findAllUser() {

        return new ResponseEntity<>(Collections.singletonMap("result", userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<Map<String, Object>> findUserById(@PathVariable String id) {
        Long userId = Long.parseLong(id);
        UserResponse user = userMapper.toResponse(userService.findById(userId));
        return new ResponseEntity<>(Collections.singletonMap("user", user), HttpStatus.OK);
    }

    @GetMapping("/findUserByLogin")
    public ResponseEntity<Object> findUserByLogin(@RequestParam("user_login") String login) {

        return new ResponseEntity<>(Collections.singletonMap("result", userService.findByLogin(login)), HttpStatus.OK);
    }

    @PatchMapping("/softDeleteUserByLogin/{login}")
    public ResponseEntity<Object> softDeleteUserByLogin(@PathVariable("login") String login) {

        User user = userService.softDelete(login);

        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }

    @PatchMapping("/banUserByLogin/{login}")
    public ResponseEntity<Object> banUserByLogin(@PathVariable("login") String login) {

        User user = userService.banByLogin(login);

        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }

    @PutMapping(value = "/updateUser/{login}")
    public ResponseEntity<Object> updateUser(@RequestParam("user_login") String login, @Valid @RequestBody UserUpdateRequest request) {
        User updatedUser = userMapper.convertUpdateRequest(request, userService.findByLogin(login));
        UserResponse userResponse = userMapper.toResponse(userService.update(updatedUser));
        return new ResponseEntity<>(Collections.singletonMap("user", userResponse), HttpStatus.OK);
    }

    @PatchMapping("/addRoleToUserByLogin/{login}")
    public ResponseEntity<Object> addRoleToUserByLogin(@PathVariable("login")String login, String role) {
        User user = userService.addRoleToUser(userService.findByLogin(login), roleService.findRoleByName(role));
        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }

    @PatchMapping("/removeUserRole/{login}")
    public ResponseEntity<Object> removeUserRole(@PathVariable("login")String login, String role) {
        User user = userService.removeUserRole(userService.findByLogin(login), roleService.findRoleByName(role));
        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }


}
