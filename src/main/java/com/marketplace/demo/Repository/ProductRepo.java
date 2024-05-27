package com.marketplace.demo.Repository;

import com.marketplace.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
