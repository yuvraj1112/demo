package io.web.demo.services.impl;

import io.web.demo.entities.Role;
import io.web.demo.entities.User;
import io.web.demo.exceptions.RoleNotFoundException;
import io.web.demo.exceptions.UserNotFoundException;
import io.web.demo.repositories.RoleRepository;
import io.web.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    RoleRepository roleRepository;

    @Mock
    UserRepository userRepository;

    @Test
    void createUser() {
        User user = new User();
        user.setFirstName("Yujraj");
        user.setLastName("Deshmukh");
        user.setId(100L);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);
        assertEquals(user,createdUser);

    }

    @Test
    void findAllUsers() {
        User user = new User();
        user.setFirstName("Parag");
        user.setLastName("Agarawal");
        user.setId(100L);

        User u2 = new User();
        u2.setFirstName("Elon");
        u2.setLastName("Musk");
        u2.setId(101L);

        Role r = new Role();
        r.setName("CEO");
        r.setId(10L);
        user.setRole(r);

        List<User> allUsers = Arrays.asList(user,u2);
        Mockito.when(userRepository.findAll()).thenReturn(allUsers);
        assertEquals(allUsers,userService.findAllUsers());
    }

    @Test
    void findUser() {
        User user = new User();
        user.setFirstName("Parag");
        user.setLastName("Agarawal");
        user.setId(100L);

        Mockito.when(userRepository.findById(100L)).thenReturn(Optional.of(user));
        assertEquals(Optional.of(user),userService.findUser(100L));
    }

    @Test
    void assignRole() {
        User user = new User();
        user.setFirstName("Parag");
        user.setLastName("Agarawal");
        user.setId(100L);

        Role r = new Role();
        r.setName("CEO");
        r.setId(10L);

        Mockito.when(roleRepository.findById(10L)).thenReturn(Optional.of(r));
        Mockito.when(userRepository.findById(100L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User savedUser = null;
        savedUser = assertDoesNotThrow(() -> userService.assignRole(100L, 10L));

        assertNotNull(savedUser);
        assertNotNull(savedUser.getRole());
        assertEquals(10L,savedUser.getRole().getId());

    }

    @Test
    void assignUnknowRole() {
        User user = new User();
        user.setFirstName("Parag");
        user.setLastName("Agarawal");
        user.setId(100L);

        Mockito.when(userRepository.findById(100L)).thenReturn(Optional.of(user));
        Mockito.when(roleRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class,() -> userService.assignRole(100L,10L));
    }

    @Test
    void assignUnknowUser() {
        Mockito.when(userRepository.findById(100L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,() -> userService.assignRole(100L,10L));
    }
}