package com.rentcar.controller;

import com.rentcar.controller.mappers.UserMapper;
import com.rentcar.controller.requests.BlockRequest;
import com.rentcar.controller.requests.UserUpdateRequest;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.User;
import com.rentcar.repository.RoleRepository;
import com.rentcar.repository.UserRepository;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
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

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final ConversionService converter;

    private final UserMapper userMapper;

    private final UserService userService;

    @GetMapping("/findAllUser")
    public ResponseEntity<Object> findAllUser() {

        return new ResponseEntity<>(Collections.singletonMap("result", repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<Map<String, Object>> findUserById(@PathVariable String id) {
        Long userId = Long.parseLong(id);
        UserResponse user = userMapper.toResponse(repository.findById(userId).get());
        return new ResponseEntity<>(Collections.singletonMap("user", user), HttpStatus.OK);
    }

    @GetMapping("/findUserByLogin")
    public ResponseEntity<Object> findUserByLogin(@RequestParam("user_login") String login) {

        return new ResponseEntity<>(Collections.singletonMap("result", repository.findByCredentialsLogin(login)), HttpStatus.OK);
    }

    @PatchMapping("/softDeleteUserById/{id}")
    public ResponseEntity<Object> softDeleteUserById(@PathVariable("id") String id, @RequestBody BlockRequest request) {

        long userId = Long.parseLong(id);
   //     Boolean isDeleted = request.getIsDeleted();
        Boolean isDeleted = true;

        User user = userService.softDelete(userId, isDeleted);

        return new ResponseEntity<>(Collections.singletonMap(user, userMapper.toResponse(user)), HttpStatus.OK);
    }

    @PutMapping(value = "/updateUser/{login}")
    public ResponseEntity<Object> updateUser(@RequestParam("user_login") String login, @Valid @RequestBody UserUpdateRequest request) {
        User updatedUser = userMapper.convertUpdateRequest(request, repository.findByCredentialsLogin(login).get());
        UserResponse userResponse = userMapper.toResponse(userService.update(updatedUser));
        return new ResponseEntity<>(Collections.singletonMap("user", userResponse), HttpStatus.OK);
    }

}
