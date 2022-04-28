package io.web.demo.controllers;

import io.web.demo.entities.User;
import io.web.demo.repositories.RoleRepository;
import io.web.demo.services.RoleService;
import io.web.demo.services.UserService;
import io.web.demo.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/api/user/all/")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
