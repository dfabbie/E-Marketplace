package com.marketplace.demo.Service;

import com.marketplace.demo.Model.Product;
import com.marketplace.demo.Repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Service Layer
@Service
public class ProductService {

    private final ProductRepo productRepository;

    public ProductService(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        Product result = productRepository.save(product);
        return result;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

}
