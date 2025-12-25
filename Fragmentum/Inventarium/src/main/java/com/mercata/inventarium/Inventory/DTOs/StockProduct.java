package com.mercata.inventarium.Inventory.DTOs;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StockProduct {
    
    private UUID product_id;
    private String product_name;
    private String product_description;
    private double product_price;

}
