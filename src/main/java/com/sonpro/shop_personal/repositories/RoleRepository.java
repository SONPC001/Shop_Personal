package com.sonpro.shop_personal.repositories;

import com.sonpro.shop_personal.entities.Account;
import com.sonpro.shop_personal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByAccounts(Account account);
}
