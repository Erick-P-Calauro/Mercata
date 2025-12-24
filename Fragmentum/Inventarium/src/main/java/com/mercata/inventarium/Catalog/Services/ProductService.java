package com.mercata.inventarium.Catalog.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercata.inventarium.Catalog.Models.Product;
import com.mercata.inventarium.Catalog.Repository.ProductRepository;
import com.mercata.inventarium.Exceptions.MissingValueException;
import com.mercata.inventarium.Exceptions.NotFoundException;
import com.mercata.inventarium.Vendors.Models.Vendor;
import com.mercata.inventarium.Vendors.Services.VendorService;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    // Razão : 
    // O Produto é vinculado a um usuário e é necessário saber se o vendedor existe.
    @Autowired
    VendorService vendorService;

    public Product saveProcut(Product product) throws MissingValueException, NotFoundException {
    
        if(!vendorService.verifyVendorExistence(product.getVendor().getVendor_id())) {
            throw new NotFoundException("Vendedor de UUID " + product.getVendor().getVendor_id() + " não foi encontrado.");
        }

        if(product.getProduct_description().equals("") || product.getProduct_description().equals(null)) {
            product.setProduct_description("Produto cadastrado em Mercata");
        }

        product.setProduct_id(UUID.randomUUID());

        return productRepository.save(product);
    }

    public Product getProductById(UUID product_id) throws NotFoundException {

        Optional<Product> product = productRepository.findById(product_id);

        if(product.isEmpty()) {
            throw new NotFoundException("Produto de UUID " + product_id + " não foi encontrado.");
        }

        return product.get();
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product product) throws NotFoundException, MissingValueException {
    
        Optional<Product> foundProduct = productRepository.findById(product.getProduct_id());

        if(foundProduct.isEmpty()) {
            throw new NotFoundException("Produto de UUID " + product.getProduct_id() + " não foi encontrado.");
        }

        Vendor product_vendor = getProductById(product.getProduct_id()).getVendor();
        product.setVendor(product_vendor);

        return productRepository.save(product);
    }

    public void deleteProduct(UUID product_id) throws NotFoundException {

        Optional<Product> foundProduct = productRepository.findById(product_id);

        if(foundProduct.isEmpty()) {
            throw new NotFoundException("Produto de UUID " + product_id + " não foi encontrado.");
        }

        productRepository.delete(foundProduct.get());
        return;
    }

}
