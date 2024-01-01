package com.ECommerce.major.Controller;

import com.ECommerce.major.GlobalData.CartData;
import com.ECommerce.major.Service.CategoryService;
import com.ECommerce.major.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.GeneratedValue;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping({"/","/home"})
    public String home(Model model){
        model.addAttribute("cartCount", CartData.products.size());
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("cartCount",CartData.products.size());
        model.addAttribute("products",productService.getAllProducts());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String getProductsByCategory(@PathVariable int id,Model model){
        model.addAttribute("category",categoryService.getAllCategories());
        model.addAttribute("cartCount",CartData.products.size());
        model.addAttribute("products",categoryService.listOfProductByCategoryId(id));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable Long id,Model model){
        model.addAttribute("cartCount",CartData.products.size());
        model.addAttribute("product",productService.getProductsById(id));
        return "viewProduct";
    }


}
