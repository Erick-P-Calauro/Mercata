package com.mercata.inventarium.Vendors.Models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.mercata.inventarium.Inventory.Models.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "tb_vendors")
public class Vendor {
    
    @MongoId
    @Indexed
    @Field(targetType = FieldType.STRING)
    private UUID vendor_id;
    
    private String vendor_name;

    private String vendor_description;

    @DBRef
    private List<Stock> vendor_stocks;

}
