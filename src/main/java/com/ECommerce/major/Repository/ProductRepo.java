package com.ECommerce.major.Repository;

import com.ECommerce.major.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
