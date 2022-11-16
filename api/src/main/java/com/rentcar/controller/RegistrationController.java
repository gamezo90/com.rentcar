package com.rentcar.controller;

import com.rentcar.controller.mappers.UserMapper;
import com.rentcar.controller.requests.UserCreateRequest;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.User;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationController {

  @Autowired private UserService userService;

  @Autowired private UserMapper userMapper;

  @PostMapping("/createUser")
  @Transactional
  public ResponseEntity<Object> addUser(@Valid @RequestBody UserCreateRequest createRequest) {

    User newUser = userMapper.convertCreateRequest(createRequest);
    UserResponse response =
            userMapper.toResponse(userService.create(newUser));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
