package com.mercata.inventarium.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercata.inventarium.Models.Product;
import com.mercata.inventarium.Repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public Product saveProcut(Product product) {
        return productRepository.save(product);
    }

}
