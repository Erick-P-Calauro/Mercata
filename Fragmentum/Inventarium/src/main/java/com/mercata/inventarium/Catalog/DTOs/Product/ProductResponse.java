package com.mercata.inventarium.Catalog.DTOs.Product;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mercata.inventarium.Catalog.DTOs.Category.CategoryResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    
    private UUID product_id;
    private UUID vendor_id;
    private String product_name;
    private String product_description;
    private double product_price;
    private List<CategoryResponse> categories;
    private Map<String, String> attributes;

}
