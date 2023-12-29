package com.ECommerce.major.Controller;

import com.ECommerce.major.Model.Category;
import com.ECommerce.major.Model.Product;
import com.ECommerce.major.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String categories(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
        public String addCategory(@ModelAttribute("category") Category category){
categoryService.addCategory(category);
return "redirect:/admin/categories";
        }
        @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
        }
        @GetMapping("/admin/categories/update/{id}")
    public String updateCategoryById(@PathVariable int id,Model model){
            Optional<Category>category=categoryService.findCategoryById(id);
            if(category.isPresent()){
                model.addAttribute("category", category.get());
                return "categoriesAdd";
            }
            else {
                return "404";
            }
        }

//    @GetMapping("/admin/getall/{id}")
//    public void getall(@PathVariable int id){
//List<Product>p=categoryService.listOfProductByCategoryId(id);
//for(Product pa:p)
//System.out.println(pa.getName());
//    }

}
