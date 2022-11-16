package com.rentcar.controller;

import com.rentcar.repository.RoleRepository;
import com.rentcar.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService repository;

    @GetMapping("/findAllRoles")
    public ResponseEntity<Object> findAllRoles() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
    }

    @GetMapping("/findRoleById")
    public ResponseEntity<Object> findRoleById(@RequestParam("id") Long roleId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findRoleById(roleId)), HttpStatus.OK);
    }


    @GetMapping("/findRolesByUserId")
    public ResponseEntity<Object> findRolesByUserId(@RequestParam("id") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findRolesByUserId(userId)), HttpStatus.OK);
    }
}
