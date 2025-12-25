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
public class StockCreate {
    
    private UUID stock_product;
    private UUID stock_vendor;
    private double stock_quantity;

}
