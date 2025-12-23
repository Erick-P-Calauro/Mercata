package com.mercata.inventarium.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mercata.inventarium.Models.Category;

public interface CategoryRepository extends MongoRepository<Category, Long> {
    
}
