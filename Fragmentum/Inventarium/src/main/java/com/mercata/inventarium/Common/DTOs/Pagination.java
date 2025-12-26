package com.mercata.inventarium.Common.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pagination {
    
    private int page_number;

    private int page_size;

    private long total_items;

    private int total_pages;

}
