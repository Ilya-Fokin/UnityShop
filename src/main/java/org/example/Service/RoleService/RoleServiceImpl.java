package org.example.Service.RoleService;

import org.example.Domains.Role;
import org.example.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findByName(String name) {
        Role role = roleRepo.findByName(name);
        return role;
    }
}
