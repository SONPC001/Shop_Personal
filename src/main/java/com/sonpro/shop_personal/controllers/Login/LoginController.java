package com.sonpro.shop_personal.controllers.Login;

import com.sonpro.shop_personal.dto.request.AccountRequest;
import com.sonpro.shop_personal.entities.Account;
import com.sonpro.shop_personal.entities.Contact;
import com.sonpro.shop_personal.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private AccountService service;

    @GetMapping("/login")
    public String loginForm() {
        return "login/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new AccountRequest());
        model.addAttribute("contact", new Contact());
        return "login/register";
    }

    public void getRequest(AccountRequest request) {
        System.out.println(request.toString() + "error");
    }
    public void getA(Account account) {
        System.out.println(account.toString() + "success");
    }

    @PostMapping("/register")
    public String registerAdd(
            @Valid @ModelAttribute("user") AccountRequest request,
            final BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            getRequest(request);
            model.addAttribute("contact", request.getContact());
            return "login/register";
        }

        // Nếu không có lỗi thì tiếp tục thực hiện logic đăng ký

        Account account = service.saveAccount(request);
        getA(account);
        return "redirect:/login";
    }

}
