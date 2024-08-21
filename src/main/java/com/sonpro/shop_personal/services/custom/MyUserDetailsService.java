package com.sonpro.shop_personal.services.custom;

import com.sonpro.shop_personal.entities.Account;
import com.sonpro.shop_personal.entities.custom.UserPrincipal;
import com.sonpro.shop_personal.services.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AccountService service;

    public MyUserDetailsService(AccountService service) {
        this.service = service;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = service.getAccountByEmail(email);
        account.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(account.get());
    }
}
