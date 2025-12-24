package com.mercata.inventarium.Catalog.Controllers;

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

import com.mercata.inventarium.Catalog.DTOs.Category.CategoryCreate;
import com.mercata.inventarium.Catalog.DTOs.Category.CategoryResponse;
import com.mercata.inventarium.Catalog.Models.Category;
import com.mercata.inventarium.Catalog.Services.CategoryService;
import com.mercata.inventarium.Exceptions.NotFoundException;
import com.mercata.inventarium.Exceptions.NotValidInputException;

@RestController
@RequestMapping("/catalog/category")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryCreate categoryCreate) throws NotValidInputException {
        
        Category category = mapper.map(categoryCreate, Category.class);
        category = categoryService.saveCategory(category);

        CategoryResponse response = mapper.map(category, CategoryResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> listCategories() {
        List<CategoryResponse> response = categoryService.listCategories().stream().map((c) -> mapper.map(c, CategoryResponse.class)).toList();

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("id") UUID category_id) throws NotFoundException {
        Category category = categoryService.getCategoryById(category_id);
        CategoryResponse response = mapper.map(category, CategoryResponse.class);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") UUID category_id, @RequestBody CategoryCreate categoryCreate) throws NotFoundException {
        Category category = mapper.map(categoryCreate, Category.class);
        category = categoryService.updateCategory(category_id, category);
    
        CategoryResponse response = mapper.map(category, CategoryResponse.class);

        return ResponseEntity.status(204).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") UUID category_id) throws NotFoundException {
        categoryService.deleteCategory(category_id);

        return ResponseEntity.status(204).build();
    }

}
