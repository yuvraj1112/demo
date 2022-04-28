package io.web.demo.services;

import io.web.demo.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    public Role createRole(Role role);
    public List<Role> findAllRoles();

    Optional<Role> findRole(Long roleId);
}
