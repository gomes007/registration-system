package com.semparar.registration.controller;

import com.semparar.registration.model.Sale;
import com.semparar.registration.service.SaleService;
import com.semparar.registration.service.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @Autowired
    SaleServiceImpl saleServiceImp;

    @PostMapping
    public ResponseEntity save(@RequestBody Sale sale) {
        Sale objSale = saleService.saveSale(sale);
        return new ResponseEntity(objSale, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Sale>> listAll() {
        return saleServiceImp.listSales();
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return saleServiceImp.findById(id).map(entity -> {
            saleServiceImp.delete(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity("Registry not found in database", HttpStatus.BAD_REQUEST));
    }


}
