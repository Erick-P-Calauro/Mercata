package com.mercata.inventarium.Catalog.Repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mercata.inventarium.Catalog.Models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, UUID> {
    
    Page<Product> findAllByVendor(PageRequest pageable, UUID vendor_id);

}
