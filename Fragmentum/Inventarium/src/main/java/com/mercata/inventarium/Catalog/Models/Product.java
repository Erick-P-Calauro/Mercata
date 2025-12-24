package com.mercata.inventarium.Catalog.Models;

import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.mercata.inventarium.Vendors.Models.Vendor;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "tb_products")
public class Product {
    
    @MongoId
    @Field(targetType = FieldType.STRING)
    private UUID product_id;

    @DBRef
    private Vendor vendor;

    private String product_name;

    private String product_description;
    
    @Field(targetType = FieldType.DOUBLE)
    private double product_price;

    @DBRef
    private List<Category> categories;

    @Nullable
    private Object attributes;

    public Product(UUID product_id) {
        this.product_id = product_id;
    }

    

}
