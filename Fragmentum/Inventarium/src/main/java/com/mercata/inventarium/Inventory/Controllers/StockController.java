package com.mercata.inventarium.Inventory.Controllers;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercata.inventarium.Catalog.Models.Product;
import com.mercata.inventarium.Exceptions.ForbiddenException;
import com.mercata.inventarium.Exceptions.NotFoundException;
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
        stock.setStock_vendor(new Vendor(vendor_id));

        stock = stockService.saveStock(stock);

        StockResponse response = mapper.map(stock, StockResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<StockResponse>> listStocks() {
        List<Stock> stocks = stockService.listStocks();
        List<StockResponse> response = stocks.stream().map((stock) -> mapper.map(stock, StockResponse.class)).toList();

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
