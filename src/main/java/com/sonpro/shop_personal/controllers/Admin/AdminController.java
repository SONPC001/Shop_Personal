package com.sonpro.shop_personal.controllers.Admin;

import com.sonpro.shop_personal.dto.request.AccountRequest;
import com.sonpro.shop_personal.dto.request.ProductRequest;
import com.sonpro.shop_personal.entities.*;
import com.sonpro.shop_personal.services.AccountService;
import com.sonpro.shop_personal.services.CategoryService;
import com.sonpro.shop_personal.services.ProductService;
import com.sonpro.shop_personal.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @GetMapping("/admin")
    public String adminHome(){
        return "admin/adminHome";
    }//page admin home

    //Accounts
    @GetMapping("/admin/users")
    public String getAcc(Model model){
        model.addAttribute("users", accountService.getAllAccounts());
        //model.addAttribute("roles", roleService.getAllRole());
        return "admin/users";
    }
    @GetMapping("/admin/users/add")
    public String getUserAdd(Model model){
        model.addAttribute("userDTO", new AccountRequest());
        model.addAttribute("roles",roleService.getAllRole());
        return "admin/usersAdd";
    }
    @PostMapping("/admin/users/update")
    public String postUserAdd(
            @ModelAttribute("userDTO") AccountRequest request
    ) {
        accountService.updateAccount(request);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable int id){
        accountService.deleteById(id);
        return "redirect:/admin/users";
    }//delete 1 user

    @GetMapping("/admin/users/update/{id}")
    public String updateUser(@PathVariable int id, Model model){
        Optional<Account> opAccount = accountService.getById(id);
        if (opAccount.isPresent()){
            Account account = opAccount.get();
            AccountRequest request = new AccountRequest();
            request.setId(account.getId());
            request.setUsername(account.getUsername());
            request.setEmail(account.getEmail());
            request.setPassword("");
            request.setContact(
                    account.getContacts().getFirst()
            );
            List<Role> roles = new ArrayList<>();
            for (Role item : account.getRoles()) {
                roles.add(item);
            }
            request.setRoles(roles);

            model.addAttribute("userDTO", request);
            model.addAttribute("roles", roleService.getAllRole());
            return "admin/usersAdd";
        } else {
            return "403";
        }
    }

    //Categories session
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/categories";
    }//view all categories

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "admin/categoriesAdd";
    }//form add new category

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.updateCategory(category);
        return "redirect:/admin/categories";
    }//form add new category > do add

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }//delete 1 category

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "admin/categoriesAdd";
        }else {
            return "404";
        }
    }//form edit category, fill old data into form

    //Products session
    @GetMapping("/admin/products")
    public String getPro(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "admin/products";
    }//view all products

    @GetMapping("/admin/products/add")
    public String getProAdd(Model model){
        model.addAttribute("productDTO", new ProductRequest());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/productsAdd";
    }// form add new product

    @PostMapping("/admin/products/add")
    public String postProAdd(
        @ModelAttribute("productDTO") ProductRequest request,
        @RequestParam("productImage") MultipartFile fileProductImage,
        @RequestParam("imgName") String imgName
    ) throws IOException {
        //convert dto > entity
        Product product = new Product();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setCategory(categoryService.getCategoryById(request.getCategory().getId()).get());
        product.setPrice(request.getPrice());
        product.setWeight(request.getWeight());
        product.setDescription(request.getDescription());
        String imageUUID;
        if(!fileProductImage.isEmpty()){
            imageUUID = fileProductImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, fileProductImage.getBytes());
        }else {
            imageUUID = imgName;
        }//save image
        product.setImage(imageUUID);

        productService.updateProduct(product);
        return "redirect:/admin/products";
    }//form add new product > do add

    @GetMapping("/admin/products/delete/{id}")
    public String deletePro(@PathVariable int id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }//delete 1 product

    @GetMapping("/admin/products/update/{id}")
    public String updatePro(@PathVariable int id, Model model){
        Optional<Product> opProduct = productService.getProductById(id);
        if (opProduct.isPresent()){
            Product product = opProduct.get();
            //convert entity > dto
            ProductRequest request = new ProductRequest();
            request.setId(product.getId());
            request.setName(product.getName());
            request.setCategory(product.getCategory());
            request.setPrice(product.getPrice());
            request.setWeight(product.getWeight());
            request.setDescription(product.getDescription());
            request.setImage(product.getImage());

            model.addAttribute("productDTO", request);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "admin/productsAdd";
        }else {
            return "404";
        }

    }//form edit product, fill old data into form
}
