package com.mercata.inventarium.Catalog.DTOs.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductUpdate {
    
    private String product_name; 
    private String product_description;
    private double product_price;

}
