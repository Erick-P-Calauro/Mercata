package com.mercata.inventarium.Inventory.DTOs;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockUpdate {
    
    private UUID stock_product;
    private double stock_quantity;

}
