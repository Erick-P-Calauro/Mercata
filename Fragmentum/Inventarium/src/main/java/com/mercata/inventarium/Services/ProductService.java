package com.mercata.inventarium.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercata.inventarium.Errors.MissingValueException;
import com.mercata.inventarium.Errors.NotFoundException;
import com.mercata.inventarium.Models.Product;
import com.mercata.inventarium.Models.Vendor;
import com.mercata.inventarium.Repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

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
