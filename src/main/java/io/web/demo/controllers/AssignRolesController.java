package io.web.demo.controllers;

import io.web.demo.entities.Role;
import io.web.demo.entities.User;
import io.web.demo.exceptions.RoleNotFoundException;
import io.web.demo.exceptions.UserNotFoundException;
import io.web.demo.services.RoleService;
import io.web.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AssignRolesController {

    private final UserService userService;

    @PostMapping("/api/assign-role/")
    public ResponseEntity<String> assignRoles(@RequestParam("user-id") long userId, @RequestParam("role-id") long roleId) throws UserNotFoundException, RoleNotFoundException {
        User user = userService.assignRole(userId, roleId);
        return ResponseEntity.ok("User " + user.getFirstName() +" "+ user.getLastName() + " assigned the role " + user.getRole().getName());
    }

}
