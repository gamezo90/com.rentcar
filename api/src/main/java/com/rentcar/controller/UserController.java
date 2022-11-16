package com.rentcar.controller;

import com.rentcar.controller.requests.RoleRequest;
import com.rentcar.controller.requests.UserCreateRequest;
import com.rentcar.controller.requests.UserUpdateRequest;
import com.rentcar.domain.Gender;
import com.rentcar.domain.Role;
import com.rentcar.domain.User;
import com.rentcar.repository.RoleRepository;
import com.rentcar.repository.UserRepository;
import com.rentcar.security.util.PrincipalUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final ConversionService converter;

    @GetMapping("/findAllUser")
    public ResponseEntity<Object> findAllUser() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
    }

    @GetMapping("/findUserById")
    public ResponseEntity<Object> findUserById(@RequestParam("id") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findById(userId)), HttpStatus.OK);
    }

    @GetMapping("/findUserByLogin")
    public ResponseEntity<Object> findUserByLogin(@RequestParam("user_login") String login) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findByCredentialsLogin(login)), HttpStatus.OK);
    }

//    @ApiOperation(value = "Finding all users with Page Info response")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
//            @ApiImplicitParam(name = "query", defaultValue = "query", required = false, paramType = "query", dataType = "string")
//
//    })

    @PostMapping("/createUser")
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateRequest createRequest) {

        User user = converter.convert(createRequest, User.class);
        User createdUser = repository.save(user);

        repository.createRoleRow(createdUser.getId(),4L);

        Map<String, Object> model = new HashMap<>();
        model.put("user", repository.findById(createdUser.getId()).get());

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PostMapping("/updateUser")
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserUpdateRequest updateRequest) {

        User user = converter.convert(updateRequest, User.class);
        User updatedUser = repository.save(user);

//        repository.createRoleRow(createdUser.getId(),4L);

        Map<String, Object> model = new HashMap<>();
        model.put("user", repository.findById(updatedUser.getId()).get());

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }
}
