package com.ECommerce.major.Controller;

import com.ECommerce.major.GlobalData.CartData;
import com.ECommerce.major.Model.Product;
import com.ECommerce.major.Service.GoogleOAuthSuccessHandler;
import com.ECommerce.major.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCartById(@PathVariable("id") long id){
        CartData.products.add(productService.getProductsById(id));
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("cartCount",CartData.products.size());
model.addAttribute("total",CartData.products.stream().mapToDouble(Product::getPrice).sum());
model.addAttribute("cart",CartData.products);
return "cart";
    }
    @GetMapping("cart/removeItem/{index}")
    public String removeItem(@PathVariable int index){
        CartData.products.remove(index);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total",CartData.products.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
}
