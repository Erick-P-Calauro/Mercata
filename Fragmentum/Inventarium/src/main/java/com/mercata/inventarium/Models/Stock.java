package com.mercata.inventarium.Models;

import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "tb_stocks")
public class Stock {
    
    @MongoId
    private UUID stock_id;

    private Product stock_product;

    private Vendor stock_vendor;

    private double stock_quantity;

    private boolean stock_available;
}
