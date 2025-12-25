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
public class StockResponse {
    
    private UUID stock_id;

    private StockProduct stock_product;

    private UUID stock_vendor_id;

    private double stock_quantity;

    private boolean stock_available;

}
