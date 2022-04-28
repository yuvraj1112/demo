package io.web.demo.services.impl;

import io.web.demo.entities.Role;
import io.web.demo.repositories.RoleRepository;
import io.web.demo.services.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void testRoleSave(){
        Role r = new Role();
        r.setId(10L);
        r.setName("Admin");
        Mockito.when(roleRepository.save(r)).thenReturn(r);
        Assertions.assertEquals(r,roleService.createRole(r));
    }

    @Test
    public void testRoleSaveNull_throwException(){
        Assertions.assertThrows(NullPointerException.class,()->roleService.createRole(null));
    }

    @Test
    public void testFindRolesWhenEmpty(){
        Mockito.when(roleRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.emptyList(),roleService.findAllRoles());
    }

    @Test
    public void testFindAll(){
        Role r = new Role();
        r.setId(10L);
        r.setName("Admin");

        Role r2 = new Role();
        r2.setId(20L);
        r2.setName("User");

        List<Role> roles = Arrays.asList(r, r2);
        Mockito.when(roleRepository.findAll()).thenReturn(roles);
        Assertions.assertEquals(roles,roleService.findAllRoles());
    }

    @Test
    public void testFindRole(){
        Role r = new Role();
        r.setId(10L);
        r.setName("Admin");
        Mockito.when(roleRepository.findById(10L)).thenReturn(Optional.of(r));
        Assertions.assertEquals(Optional.of(r),roleService.findRole(10L));
    }

}
