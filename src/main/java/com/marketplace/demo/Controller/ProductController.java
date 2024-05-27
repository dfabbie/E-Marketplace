package com.marketplace.demo.Controller;

import com.marketplace.demo.Model.Product;
import com.marketplace.demo.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Controller and API LAYER
@RestController
@RequestMapping(path = "/api/v0")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product", produces = "application/json")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping(value = "/product/{id}", produces = "application/json")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping(value = "/products", produces = "application/json" )
    public List<Product> listProduct() {
        return productService.listProduct();
    }

    @DeleteMapping(value = "/product/{id}", produces ="application/json")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
