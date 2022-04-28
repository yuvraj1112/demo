package io.web.demo.services;

import io.web.demo.entities.Role;
import io.web.demo.entities.User;
import io.web.demo.exceptions.RoleNotFoundException;
import io.web.demo.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> findAllUsers();

    Optional<User> findUser(Long userId);

    User assignRole(long userId, long roleId) throws UserNotFoundException, RoleNotFoundException;
}
