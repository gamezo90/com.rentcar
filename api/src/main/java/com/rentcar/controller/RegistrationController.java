package com.rentcar.controller;

import com.rentcar.controller.mappers.UserMapper;
import com.rentcar.controller.requests.UserRequests.UserCreateRequest;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.User;
import com.rentcar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Tag(name = "Registration controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationController {

   private final UserService userService;

   private final UserMapper userMapper;
    @Operation(summary = "Create new users")
    @PostMapping("/createUser")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserCreateRequest createRequest) {
        User newUser = userMapper.convertCreateRequest(createRequest);
        userService.checkUserLoginAndEmailForNotExistInDB(newUser);
        UserResponse response = userMapper.toResponse(userService.create(newUser));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
