package com.mercata.inventarium.Inventory.Models;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.mercata.inventarium.Catalog.Models.Product;
import com.mercata.inventarium.Vendors.Models.Vendor;

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
    @Field(targetType = FieldType.STRING)
    private UUID stock_id;
    
    @DBRef
    private Product stock_product;

    @DBRef
    private Vendor stock_vendor;

    private double stock_quantity;

    private boolean stock_available;
}
