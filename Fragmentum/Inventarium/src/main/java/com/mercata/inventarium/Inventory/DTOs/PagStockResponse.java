package com.mercata.inventarium.Inventory.DTOs;

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
public class PagStockResponse {
    
    private Pagination pagination;

    private List<StockResponse> data;

}
