package com.mercata.inventarium.Vendors.Controllers;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercata.inventarium.Exceptions.NotFoundException;
import com.mercata.inventarium.Vendors.DTOs.Vendor.VendorCreate;
import com.mercata.inventarium.Vendors.DTOs.Vendor.VendorResponse;
import com.mercata.inventarium.Vendors.Models.Vendor;
import com.mercata.inventarium.Vendors.Services.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    
    @Autowired
    ModelMapper mapper;

    @Autowired
    VendorService vendorService;

    @PostMapping("/save") // Talvez não seja utilizado por conta dos eventos que virão do serviço de usuários
    public ResponseEntity<VendorResponse> saveVendor(@RequestBody VendorCreate vendorCreate ) {
        
        Vendor vendor = mapper.map(vendorCreate, Vendor.class);
        vendor = vendorService.saveVendor(vendor);

        VendorResponse response = mapper.map(vendor, VendorResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<VendorResponse>> listVendors() {
        
        List<Vendor> vendors = vendorService.listVendors();
        List<VendorResponse> response = vendors.stream().map((v) -> mapper.map(v, VendorResponse.class)).toList();

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponse> getVendor(@PathVariable("id") UUID vendor_id) throws NotFoundException {

        Vendor vendor = vendorService.getVendorById(vendor_id);
        VendorResponse response = mapper.map(vendor, VendorResponse.class);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VendorResponse> updateVendor(@PathVariable("id") UUID vendor_id, @RequestBody VendorCreate vendorUpdate) throws NotFoundException {
        Vendor vendor = mapper.map(vendorUpdate, Vendor.class);
        vendor.setVendor_id(vendor_id);
        vendor = vendorService.updateVendor(vendor);

        VendorResponse response = mapper.map(vendor, VendorResponse.class);
        
        return ResponseEntity.status(204).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteVendor(@PathVariable("id") UUID vendor_id) throws NotFoundException {
        vendorService.deleteVendor(vendor_id);

        return ResponseEntity.status(204).build();
    }

}
