package com.sonpro.shop_personal.services;

import com.sonpro.shop_personal.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRole();
    Optional<Role> findRoleById(int id);

}
