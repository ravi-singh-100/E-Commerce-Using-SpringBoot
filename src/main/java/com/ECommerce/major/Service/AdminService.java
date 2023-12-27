package com.ECommerce.major.Service;

import com.ECommerce.major.Model.Category;
import com.ECommerce.major.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminService {
    @Autowired
    CategoryRepo categoryRepo;
    public List<Category>getAllCategories(){
        return categoryRepo.findAll();
    }
    public void addCategory(Category category){
        categoryRepo.save(category);
    }
}
