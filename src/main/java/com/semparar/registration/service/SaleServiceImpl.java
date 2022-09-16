package com.semparar.registration.service;

import com.semparar.registration.model.Employee;
import com.semparar.registration.model.Sale;
import com.semparar.registration.repository.SaleRepository;
import com.semparar.registration.service.exceptions.ObjctNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;


    @Override
    public Sale saveSale(Sale sale) {
        sale.setTotal(sale.getQuantity() * sale.getUnitPrice());
        return saleRepository.save(sale);
    }

    public ResponseEntity<List<Sale>> listSales(){
        try {
            return ResponseEntity.ok(this.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public List<Sale> findAll(){
        return saleRepository.findAll();
    }

    public Optional<Sale> findById(Long id){
        return saleRepository.findById(id);
    }

    public Sale findId(Long id) {
        Optional<Sale> obj = saleRepository.findById(id);
        return obj.orElseThrow(() -> new ObjctNotFoundException(
                "Registry not found! Id: " + id + ", Type: " + Sale.class.getName()));

    }

    @Transactional
    public void delete(Sale sale){
        Objects.requireNonNull(sale.getId());
        saleRepository.delete(sale);
    }



}
