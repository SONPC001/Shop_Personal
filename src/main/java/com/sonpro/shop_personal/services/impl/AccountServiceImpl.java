package com.sonpro.shop_personal.services.impl;

import com.sonpro.shop_personal.dto.request.AccountRequest;
import com.sonpro.shop_personal.entities.Account;
import com.sonpro.shop_personal.entities.Contact;
import com.sonpro.shop_personal.entities.Role;
import com.sonpro.shop_personal.repositories.AccountRepository;
import com.sonpro.shop_personal.repositories.ContactRepository;
import com.sonpro.shop_personal.repositories.RoleRepository;
import com.sonpro.shop_personal.services.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final ContactRepository contactRepository;

    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, ContactRepository contactRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.contactRepository = contactRepository;
    }

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder(12);


    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Page<Account> getAllPage(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Optional<Account> getById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public boolean deleteById(int id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Account saveAccount(AccountRequest request) {
        Account ac = new Account();
        getAc_Ct(request, ac);

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(3).orElse(null));
        ac.setRoles(roles);

        return accountRepository.save(ac);
    }

    private void getAc_Ct(AccountRequest request, Account ac) {
        ac.setUsername(request.getUsername());
        ac.setEmail(request.getEmail());
        ac.setPassword(encoder.encode(request.getPassword()));

        List<Contact> contacts = new ArrayList<>();
        Contact contact = request.getContact();
        contact.setAccount(ac);
        contacts.add(contact);
        ac.setContacts(contacts);
    }

    @Override
    public Account updateAccount(AccountRequest request) {
        Account ac = new Account();
        ac.setId(request.getId());
        getAc_Ct(request, ac);

        List<Role> roles = new ArrayList<>(request.getRoles());
        ac.setRoles(roles);

        return accountRepository.save(ac);
    }

    @Override
    public Account update_ac(AccountRequest request) {
        Account ac = new Account();
        ac.setId(request.getId());
        ac.setUsername(request.getUsername());
        ac.setEmail(request.getEmail());
        ac.setPassword(encoder.encode(request.getPassword()));
        List<Contact> contacts = new ArrayList<>(
                contactRepository.findAllByAccount_Id(
                        request.getId()
                )
        );
        ac.setContacts(contacts);
        List<Role> roles = new ArrayList<>(
                roleRepository.findAllByAccounts(ac)
        );
        ac.setRoles(roles);

        return accountRepository.save(ac);
    }

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
