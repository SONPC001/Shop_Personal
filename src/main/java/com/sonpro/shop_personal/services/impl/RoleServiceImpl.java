package com.sonpro.shop_personal.services.impl;

import com.sonpro.shop_personal.entities.Role;
import com.sonpro.shop_personal.repositories.RoleRepository;
import com.sonpro.shop_personal.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> getAllRole() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(int id) {
        return repository.findById(id);
    }
}
