package pl.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.model.Role;
import pl.com.model.RoleType;
import pl.com.repository.RoleRepository;
import pl.com.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleById(Long id) {
        return roleRepository.findOne(id);
    }

    public List<Role> findAllRolesByType(RoleType type) {
        return roleRepository.findAllByType(type);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
