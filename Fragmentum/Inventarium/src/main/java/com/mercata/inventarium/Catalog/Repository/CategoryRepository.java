package com.mercata.inventarium.Catalog.Repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mercata.inventarium.Catalog.Models.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, UUID> {
    
}
