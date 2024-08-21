package com.sonpro.shop_personal.config;

import com.sonpro.shop_personal.dto.request.AccountRequest;
import com.sonpro.shop_personal.entities.Account;
import com.sonpro.shop_personal.entities.Contact;
import com.sonpro.shop_personal.entities.Role;
import com.sonpro.shop_personal.services.AccountService;
import com.sonpro.shop_personal.services.ContactService;
import com.sonpro.shop_personal.services.RoleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private RoleService roleService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final RedirectStrategy strategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email = token.getPrincipal().getAttribute("email");
        if (accountService.getAccountByEmail(email).isPresent()) {
            strategy.sendRedirect(request, response, "/login");
        } else {
            AccountRequest account = new AccountRequest();
            account.setUsername(token.getPrincipal().getAttribute("username"));
            account.setEmail(email);
            account.setPassword(encoder.encode("123"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.findRoleById(2).orElse(null));
            roles.add(roleService.findRoleById(3).orElse(null));
            account.setRoles(roles);

            accountService.saveAccount(account);
        }
    }
}
