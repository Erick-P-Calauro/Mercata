package com.mercata.inventarium.Inventory.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mercata.inventarium.Catalog.Services.ProductService;
import com.mercata.inventarium.Exceptions.ForbiddenException;
import com.mercata.inventarium.Exceptions.NotFoundException;
import com.mercata.inventarium.Inventory.Models.Stock;
import com.mercata.inventarium.Inventory.Repository.StockRepository;
import com.mercata.inventarium.Vendors.Services.VendorService;

@Service
public class StockService {
    
    @Autowired
    StockRepository stockRepository;

    // Motivo : 
    // - Verificar a existência do produto do estoque para cadastro.
    // - Atualizar o produto do estoque
    @Autowired
    ProductService productService;

    // Motivo : 
    // - Verificar a existência do dono do estoque para cadastro.
    @Autowired
    VendorService vendorService;

    public Stock saveStock(Stock stock) throws NotFoundException, ForbiddenException {

        UUID product_id = stock.getStock_product().getProduct_id();
        UUID vendor_id = stock.getStock_vendor().getVendor_id();

        if(!vendorService.verifyVendorExistence(vendor_id)) {
            throw new NotFoundException("Vendedor com UUID " + vendor_id + " não foi encontrado.");
        }

        if(!productService.verifyProductExistence(product_id)) {
            throw new NotFoundException("Produto com UUID " + product_id + " não foi encontrado.");
        }

        // Atribuindo relacionamentos manualmente
        // Vendor -> Referência
        // Produto -> Incorporação
        stock.setStock_product(productService.getProductById(product_id));

        if(!stock.getStock_product().getVendor().getVendor_id().equals(vendor_id)) {
            throw new ForbiddenException("O dono do estoque deve ser o dono do produto.");
        }

        if(stock.getStock_quantity() < 0) {
            stock.setStock_quantity(0);
        }

        stock.setStock_id(UUID.randomUUID());
        stock.setStock_available(false);

        return stockRepository.save(stock);
    }

    public Page<Stock> listStocks(PageRequest pageable) {
        return stockRepository.findAll(pageable);
    }

    public List<Stock> listStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockById(UUID stock_id) throws NotFoundException {
        Optional<Stock> stock = stockRepository.findById(stock_id);

        if(stock.isEmpty()) {
            throw new NotFoundException("Estoque com UUID " + stock_id + " não foi encontrado.");
        }

        return stock.get();
    }

    public Stock updateStock(UUID stock_id, Stock stock) throws NotFoundException {

        Optional<Stock> foundStock = stockRepository.findById(stock_id);

        if(foundStock.isEmpty()) {
            throw new NotFoundException("Estoque com UUID " + stock_id + " não foi encontrado");
        }

        UUID product_id = stock.getStock_product().getProduct_id();

        if(!productService.verifyProductExistence(product_id)) {
            throw new NotFoundException("Produto com UUID " + product_id + " não foi encontrado.");    
        }

        Stock oldStock = foundStock.get();

        // Valores não atualizáveis
        stock.setStock_vendor(oldStock.getStock_vendor());
        stock.setStock_id(stock_id);

        // Valores atualizáveis
        // stock_quantity
        stock.setStock_product(productService.getProductById(product_id));
        stock.setStock_available(false);
        
        return stockRepository.save(stock);
    }

    public void deleteStock(UUID stock_id) throws NotFoundException {

        Optional<Stock> stock = stockRepository.findById(stock_id);

        if(stock.isEmpty()) {
            throw new NotFoundException("Estoque com UUID " + stock_id + " não foi encontrado.");
        }

        stockRepository.delete(stock.get());
        return;
    }

    public boolean activateStock(UUID stock_id) throws NotFoundException {

        Stock stock = getStockById(stock_id);

        if(stock.getStock_quantity() <= 0) {
            return false;
        }

        stock.setStock_available(true);
        stockRepository.save(stock);

        return true;
    }

    // list by categories and vendors
    // validStockQuantity
}
