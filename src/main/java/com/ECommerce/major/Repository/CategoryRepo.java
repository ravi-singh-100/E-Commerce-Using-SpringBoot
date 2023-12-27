package com.ECommerce.major.Repository;

import com.ECommerce.major.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
