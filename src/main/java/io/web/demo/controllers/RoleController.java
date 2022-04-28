package io.web.demo.controllers;

import io.web.demo.entities.Role;
import io.web.demo.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleController {

    public final RoleService roleService;

    @PostMapping("/api/role/")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        return  ResponseEntity.ok(roleService.createRole(role));
    }


    @GetMapping("/api/role/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok(roleService.findAllRoles());
    }
}
