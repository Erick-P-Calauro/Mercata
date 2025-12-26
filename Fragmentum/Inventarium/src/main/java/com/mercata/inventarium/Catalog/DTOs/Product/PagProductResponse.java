package com.mercata.inventarium.Catalog.DTOs.Product;

import java.util.List;

import com.mercata.inventarium.Common.DTOs.Pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PagProductResponse {
    
    Pagination pagination;

    List<ProductResponse> data;

}
