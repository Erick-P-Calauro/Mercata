package com.mercata.inventarium.Vendors.Repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mercata.inventarium.Vendors.Models.Vendor;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, UUID> {
    
}
