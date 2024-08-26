package com.sonpro.shop_personal.services.impl;

import com.sonpro.shop_personal.entities.Contact;
import com.sonpro.shop_personal.repositories.ContactRepository;
import com.sonpro.shop_personal.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    @Override
    public Optional<Contact> findContactById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Contact> getAllContactByAccountId(int id) {
        return repository.findAllByAccount_Id(id);
    }
}
