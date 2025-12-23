package com.mercata.inventarium.Repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mercata.inventarium.Models.Vendor;

public interface VendorRepository extends MongoRepository<Vendor, UUID> {
    
}
