package com.mercata.inventarium.Repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mercata.inventarium.Models.Stock;

public interface StockRepository extends MongoRepository<Stock, UUID>  {
    
}
