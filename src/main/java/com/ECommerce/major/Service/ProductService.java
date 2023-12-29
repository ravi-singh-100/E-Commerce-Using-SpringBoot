package com.ECommerce.major.Service;

import com.ECommerce.major.Model.Product;
import com.ECommerce.major.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    public void addProducts(Product product){
        productRepo.save(product);
    }
    public Product getProductsById(Long id){
       return productRepo.findById(id).get();
    }
    public void deletProducsts(Long id){
        productRepo.deleteById(id);
    }
}
