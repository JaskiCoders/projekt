package pl.com.service;

import pl.com.model.Role;
import pl.com.model.RoleType;

import java.util.List;

public interface RoleService {
    Role findRoleById(Long id);
    List<Role> findAllRolesByType(RoleType type);
    List<Role> findAll();
}
