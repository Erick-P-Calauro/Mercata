package com.mercata.inventarium.Vendors.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.mercata.inventarium.Exceptions.MissingValueException;
import com.mercata.inventarium.Exceptions.NotFoundException;
import com.mercata.inventarium.Vendors.DTOs.Vendor.VendorPayload;
import com.mercata.inventarium.Vendors.Models.Vendor;
import com.mercata.inventarium.Vendors.Repository.VendorRepository;

@Service
public class VendorService {
    
    @Autowired
    VendorRepository vendorRepository;
    
    @Autowired
    ModelMapper mapper;
    
    // Método para cadastro por API de acordo com VendorCreate
    public Vendor saveVendor(Vendor vendor) {

        // Atribuição manual de UUID
        vendor.setVendor_id(UUID.randomUUID());

        // Preenchendo valor padrão de vendor_description;
        if(vendor.getVendor_description().equals(null) || vendor.getVendor_description().length() == 0) {
            vendor.setVendor_description("Vendedor em Mercata.");
        }

        // Valor padrão de vendor_stocks
        vendor.setVendor_stocks(new ArrayList<>());

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

    public void deleteVendor(UUID vendor_id) throws NotFoundException {
        Optional<Vendor> foundVendor = vendorRepository.findById(vendor_id);

        if(foundVendor.isEmpty()) {
            throw new NotFoundException("Vendedor de UUID " + vendor_id + " não encontrado.");
        }

        vendorRepository.delete(foundVendor.get());
    }

    // Cadastro de vendor por mensageria
    public void processVendorCreatedPayload(Message<VendorPayload> vendorPayload) throws MissingValueException {
        VendorPayload payload = vendorPayload.getPayload();

        if( payload.getVendor_id().equals(null) || 
            payload.getVendor_name().equals(null) || 
            payload.getVendor_name().length() < 3
        ) {
            throw new MissingValueException("Valores requeridos para criação de vendor estão ausentes ou inválidos.");
        }

        Optional<Vendor> foundVendor = vendorRepository.findById(payload.getVendor_id());

        // Evento duplicado de criação de vendedores.
        if(foundVendor.isPresent()) {
            return;
        }

        Vendor vendor = mapper.map(payload, Vendor.class);
        vendor.setVendor_description("Vendedor em Mercata.");
        vendor.setVendor_stocks(new ArrayList<>());
    
        vendorRepository.save(vendor);
        return;
    }

    public boolean verifyVendorExistence(UUID vendor_id) {

        Optional<Vendor> vendor = vendorRepository.findById(vendor_id);

        if(vendor.isPresent()) {
            return true;
        }

        return false;
    }

}
