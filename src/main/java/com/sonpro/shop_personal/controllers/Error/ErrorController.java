package com.sonpro.shop_personal.controllers.Error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/403")
    public String errorPage() {
        return "error";
    }
}
