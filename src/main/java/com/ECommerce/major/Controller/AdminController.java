package com.ECommerce.major.Controller;

import com.ECommerce.major.Model.Category;
import com.ECommerce.major.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

public class AdminController {
    @Autowired
    AdminService adminService;
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String categories(Model model){
        model.addAttribute("categories",adminService.getAllCategories());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
        public String addCategory(@ModelAttribute("category") Category category){
adminService.addCategory(category);
return "redirect:/admin/categories";
        }

}
