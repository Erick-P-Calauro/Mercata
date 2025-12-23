package com.mercata.inventarium.Repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mercata.inventarium.Models.Product;

public interface ProductRepository extends MongoRepository<Product, UUID> {
    
}
