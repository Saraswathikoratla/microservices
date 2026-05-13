package com.saraswathi.service;

import com.saraswathi.entity.Product;
import com.saraswathi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }
    public Product findById(long id){
        return repository.findById(id).orElse(null);
    }
}