package com.sonpro.shop_personal.services;

import com.sonpro.shop_personal.dto.request.AccountRequest;
import com.sonpro.shop_personal.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();
    Page<Account> getAllPage(Pageable pageable);


    Optional<Account> getById(int id);
    Account saveAccount(AccountRequest request);
    Account updateAccount(int id, AccountRequest request);
    boolean deleteById(int id);

    Optional<Account> getAccountByEmail(String email);
}
