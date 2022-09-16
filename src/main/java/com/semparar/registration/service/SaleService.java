package com.semparar.registration.service;

import com.semparar.registration.model.Sale;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("saleService")
public interface SaleService {

    Sale saveSale(Sale sale);

    void delete(Sale sale);

    List<Sale> findAll();
}
