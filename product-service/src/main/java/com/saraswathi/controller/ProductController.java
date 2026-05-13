package com.saraswathi.controller;


import com.saraswathi.entity.Product;
import com.saraswathi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    // 🔥 Get all products
    @GetMapping
    public List<Product> getProducts(
            @RequestHeader(value="X-User",required = false) String user,
            @RequestHeader(value = "X-Role", required = false) String role) {

        System.out.println("User: " + user + " Role: " + role);
//log.info("user role: {}",role);
        return service.getAllProducts();
    }

    // 🔥 Add product (admin use later)
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.findById(id);
    }
}