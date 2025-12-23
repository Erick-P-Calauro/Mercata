package com.mercata.inventarium.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mercata.inventarium.Errors.NotFoundException;
import com.mercata.inventarium.Models.Vendor;
import com.mercata.inventarium.Repository.VendorRepository;

@Service
public class VendorService {
    
    @Autowired
    VendorRepository vendorRepository;

    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> listVendors() {
        return vendorRepository.findAll();        
    }

    public Vendor getVendorById(UUID vendor_id) throws NotFoundException {
        Optional<Vendor> vendor = vendorRepository.findById(vendor_id);

        if(vendor.isEmpty()) {
            throw new NotFoundException("Vendedor de UUID " + vendor_id + " não encontrado.");
        }

        return vendor.get();
    }

    public Vendor updateVendor(Vendor vendor) throws NotFoundException {
        Optional<Vendor> foundVendor = vendorRepository.findById(vendor.getVendor_id());

        if(foundVendor.isEmpty()) {
            throw new NotFoundException("Vendedor de UUID " + vendor.getVendor_id() + " não encontrado.");
        }

        return vendorRepository.save(vendor);
    }

    public void deleteVendor(Vendor vendor) {
        vendorRepository.delete(vendor);
    }

}
