package io.web.demo.services.impl;

import io.web.demo.entities.Role;
import io.web.demo.entities.User;
import io.web.demo.exceptions.RoleNotFoundException;
import io.web.demo.exceptions.UserNotFoundException;
import io.web.demo.repositories.RoleRepository;
import io.web.demo.repositories.UserRepository;
import io.web.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }


    @Override
    public User assignRole(long userId, long roleId) throws UserNotFoundException, RoleNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(roleId));
        user.setRole(role);
        userRepository.save(user);
        return user;
    }
}
