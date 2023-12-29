package com.ECommerce.major.Service;

import com.ECommerce.major.Model.Category;
import com.ECommerce.major.Model.Product;
import com.ECommerce.major.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    public List<Category>getAllCategories(){
        return categoryRepo.findAll();
    }
    public void addCategory(Category category){
        categoryRepo.save(category);
    }

    public void deleteCategoryById(int id) {
        categoryRepo.deleteById(id);
    }

    public Optional<Category> findCategoryById(int id) {
        return categoryRepo.findById(id);
    }
    public List<Product> listOfProductByCategoryId(int id){
        return categoryRepo.findById(id).get().getProducts();
    }
}
