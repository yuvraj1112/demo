package io.web.demo.services.impl;

import io.web.demo.entities.Role;
import io.web.demo.repositories.RoleRepository;
import io.web.demo.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        if(role == null) throw new NullPointerException();
       return  roleRepository.save(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRole(Long roleId) {
        return roleRepository.findById(roleId);
    }
}
