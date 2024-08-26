package com.sonpro.shop_personal.repositories;

import com.sonpro.shop_personal.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByAccount_Id(int id);
}
