package com.mercata.inventarium.Catalog.DTOs.Product;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductCreate {

    private String product_name; 
    private UUID vendor_id;
    private String product_description;
    private double product_price;

}
