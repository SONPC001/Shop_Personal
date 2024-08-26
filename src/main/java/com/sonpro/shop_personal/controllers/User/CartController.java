package com.sonpro.shop_personal.controllers.User;

import com.sonpro.shop_personal.entities.Product;
import com.sonpro.shop_personal.global.GlobalData;
import com.sonpro.shop_personal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", GlobalData.Cart.size());
        model.addAttribute("total", GlobalData.Cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.Cart);
        return "user/cart";
    }//page cart

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.Cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }//click add from page viewProduct

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalData.Cart.remove(index);
        return "redirect:/cart";
    } // delete 1 product

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("cartCount", GlobalData.Cart.size());
        model.addAttribute("total", GlobalData.Cart.stream().mapToDouble(Product::getPrice).sum());
        //model.addAttribute("cart", GlobalData.cart);
        return "user/checkout";
    } // checkout totalPrice
}
