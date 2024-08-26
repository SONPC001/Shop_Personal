package com.sonpro.shop_personal.services;

import com.sonpro.shop_personal.entities.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> getAllContacts();
    Optional<Contact> findContactById(int id);
    List<Contact> getAllContactByAccountId(int id);
}
