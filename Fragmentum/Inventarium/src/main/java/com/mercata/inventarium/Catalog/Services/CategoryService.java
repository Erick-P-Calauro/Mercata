package com.mercata.inventarium.Catalog.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercata.inventarium.Catalog.Models.Category;
import com.mercata.inventarium.Catalog.Repository.CategoryRepository;
import com.mercata.inventarium.Exceptions.NotFoundException;
import com.mercata.inventarium.Exceptions.NotValidInputException;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;

    public Category saveCategory(Category category) throws NotValidInputException {

        if(category.getCategory_name().equals(null) || category.getCategory_name().length() < 3) {
            throw new NotValidInputException("O nome da categoria deve estar presente e ter ao menos três caracteres.");
        }

        category.setCategory_id(UUID.randomUUID());

        return categoryRepository.save(category);
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID category_id) throws NotFoundException {
        Optional<Category> category = categoryRepository.findById(category_id);

        if(category.isEmpty()) {
            throw new NotFoundException("Categoria com UUID " + category_id + " não foi encontrada.");
        }

        return category.get();
    }

    public Category updateCategory(UUID category_id, Category category) throws NotFoundException {
        
        if(categoryRepository.findById(category_id).isEmpty()) {
            throw new NotFoundException(("Categoria com UUID " + category_id + " não foi encontrada."));
        }

        category.setCategory_id(category_id);

        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID category_id) throws NotFoundException {
        Optional<Category> category = categoryRepository.findById(category_id);

        if(category.isEmpty()) {
            throw new NotFoundException(("Categoria com UUID " + category_id + " não foi encontrada."));
        }

        categoryRepository.delete(category.get());
        return;
    }

    public boolean verifyCategoryExistence(UUID category_id) {
        return categoryRepository.existsById(category_id);
    }

}
