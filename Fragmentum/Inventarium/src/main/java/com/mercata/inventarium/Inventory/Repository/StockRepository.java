package com.mercata.inventarium.Inventory.Repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mercata.inventarium.Inventory.Models.Stock;

@Repository
public interface StockRepository extends MongoRepository<Stock, UUID>  {
    
    Page<Stock> findAllByVendor(PageRequest pageable, UUID vendor_id);

}
