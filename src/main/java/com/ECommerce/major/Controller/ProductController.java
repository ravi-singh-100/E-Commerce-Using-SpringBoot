package com.ECommerce.major.Controller;

import com.ECommerce.major.DTO.ProductDTO;
import com.ECommerce.major.Model.Product;
import com.ECommerce.major.Service.CategoryService;
import com.ECommerce.major.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductController {
    private static String DIR=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/admin/products")
    public String getAllProducts(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String addProducts(Model model){
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String addproducts(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("productImage")MultipartFile file,@RequestParam("imgName") String imgName) throws IOException {
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.findCategoryById(productDTO.getCategoryId()).get());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID=file.getOriginalFilename();
            Path filenameandpath= Paths.get(DIR,imageUUID);
            Files.write(filenameandpath,file.getBytes());
        }
        else {
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        productService.addProducts(product);
return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProducts(@PathVariable long id){
        productService.deletProducsts(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProducts(@PathVariable long id,Model model){
        ProductDTO productDTO=new ProductDTO();
        Product product=productService.getProductsById(id);
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setId(product.getId());
        productDTO.setImageName(product.getImageName());
        productDTO.setWeight(product.getWeight());
        productDTO.setPrice(product.getPrice());
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("productDTO",productDTO);
        return "productsAdd";
    }
}
