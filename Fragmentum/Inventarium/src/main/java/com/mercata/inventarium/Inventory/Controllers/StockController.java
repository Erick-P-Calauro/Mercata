package com.mercata.inventarium.Inventory.Controllers;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercata.inventarium.Catalog.Models.Product;
import com.mercata.inventarium.Common.DTOs.Pagination;
import com.mercata.inventarium.Exceptions.ForbiddenException;
import com.mercata.inventarium.Exceptions.NotFoundException;
import com.mercata.inventarium.Inventory.DTOs.PagStockResponse;
import com.mercata.inventarium.Inventory.DTOs.StockCreate;
import com.mercata.inventarium.Inventory.DTOs.StockResponse;
import com.mercata.inventarium.Inventory.DTOs.StockUpdate;
import com.mercata.inventarium.Inventory.Models.Stock;
import com.mercata.inventarium.Inventory.Services.StockService;
import com.mercata.inventarium.Vendors.Models.Vendor;

@RestController
@RequestMapping("/inventory/stock")
public class StockController {
    
    @Autowired
    StockService stockService;

    @Autowired
    ModelMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<StockResponse> saveStock(@RequestBody StockCreate stockCreate) throws NotFoundException, ForbiddenException {
        
        // O mapeando dos relacionamentos é feito manualmente por problema no mapper
        UUID vendor_id = stockCreate.getStock_vendor();
        UUID product_id = stockCreate.getStock_product();
        
        Stock stock = mapper.map(stockCreate, Stock.class);
        stock.setStock_product(new Product(product_id));
        stock.setVendor(new Vendor(vendor_id));

        stock = stockService.saveStock(stock);

        StockResponse response = mapper.map(stock, StockResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<PagStockResponse> listAllStocks(
        @RequestParam(defaultValue = "0") int page_number,
        @RequestParam(defaultValue = "10") int page_size
    ) {
        PageRequest pageable = PageRequest.of(page_number, page_size);
        Page<Stock> stocks = stockService.listStocks(pageable);

        Pagination pagination_response = new Pagination(stocks.getNumber(), stocks.getSize(), stocks.getNumberOfElements(), stocks.getTotalPages());
        List<StockResponse> stocks_response = stocks.getContent().stream().map((stock) -> mapper.map(stock, StockResponse.class)).toList();

        PagStockResponse response = new PagStockResponse(pagination_response, stocks_response);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/vendor/{vendor_id}")
    public ResponseEntity<PagStockResponse> listStocksByVendor(
        @RequestParam(defaultValue = "0") int page_number,
        @RequestParam(defaultValue = "10") int page_size,
        @PathVariable("vendor_id") UUID vendor_id ) throws NotFoundException {

        PageRequest pageable = PageRequest.of(page_number, page_size);
        Page<Stock> stocks = stockService.listStocksByVendor(pageable, vendor_id);

        Pagination pagination_response = new Pagination(stocks.getNumber(), stocks.getSize(), stocks.getNumberOfElements(), stocks.getTotalPages());
        List<StockResponse> stocks_response = stocks.getContent().stream().map((stock) -> mapper.map(stock, StockResponse.class)).toList();

        PagStockResponse response = new PagStockResponse(pagination_response, stocks_response);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponse> getStock(@PathVariable("id") UUID stock_id) throws NotFoundException {
        Stock stock = stockService.getStockById(stock_id);
        StockResponse response = mapper.map(stock, StockResponse.class);

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StockResponse> updateStock(@PathVariable("id") UUID stock_id, @RequestBody StockUpdate stockUpdate) throws NotFoundException {
        UUID product_id = stockUpdate.getStock_product();
        
        Stock stock = mapper.map(stockUpdate, Stock.class);
        stock.setStock_product(new Product(product_id));

        stock = stockService.updateStock(stock_id, stock);

        StockResponse response = mapper.map(stock, StockResponse.class);

        return ResponseEntity.status(204).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStock(@PathVariable("id") UUID stock_id) throws NotFoundException {
        stockService.deleteStock(stock_id);

        return ResponseEntity.status(204).build();
    }
    
}
