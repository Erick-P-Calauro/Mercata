package com.mercata.inventarium.Catalog.Controllers;

import java.util.ArrayList;
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

import com.mercata.inventarium.Catalog.DTOs.Product.ProductCreate;
import com.mercata.inventarium.Catalog.DTOs.Product.ProductResponse;
import com.mercata.inventarium.Catalog.Models.Category;
import com.mercata.inventarium.Catalog.Models.Product;
import com.mercata.inventarium.Catalog.Services.ProductService;
import com.mercata.inventarium.Exceptions.NotValidInputException;
import com.mercata.inventarium.Exceptions.NotFoundException;

@RestController
@RequestMapping("/catalog/product")
public class ProductController {
    
    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> saveProcut(@RequestBody ProductCreate productCreate) throws NotValidInputException, NotFoundException {

        ArrayList<Category> product_categories = new ArrayList<>();
        productCreate.getCategories_id().stream().forEach((cat_id) -> product_categories.add(new Category(cat_id))); 

        Product product = mapper.map(productCreate, Product.class);
        product.setCategories(product_categories);

        product = productService.saveProcut(product);

        ProductResponse response = mapper.map(product, ProductResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> listProducts() {
        
        List<Product> products = productService.listProducts();
        List<ProductResponse> response = products.stream().map((p) -> mapper.map(p, ProductResponse.class)).toList();

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") UUID product_id) throws NotFoundException {

        Product product = productService.getProductById(product_id);
        ProductResponse response = mapper.map(product, ProductResponse.class);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") UUID product_id, @RequestBody ProductCreate productCreate) throws NotFoundException, NotValidInputException {

        ArrayList<Category> product_categories = new ArrayList<>();
        productCreate.getCategories_id().stream().forEach((cat_id) -> product_categories.add(new Category(cat_id))); 

        Product product = mapper.map(productCreate, Product.class);
        product.setCategories(product_categories);
        product.setProduct_id(product_id);

        product = productService.updateProduct(product);
        ProductResponse response = mapper.map(product, ProductResponse.class);

        return ResponseEntity.status(204).body(response);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") UUID product_id) throws NotFoundException {
        productService.deleteProduct(product_id);
        
        return ResponseEntity.status(204).build();
    }


}
