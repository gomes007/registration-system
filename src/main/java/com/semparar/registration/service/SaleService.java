package com.semparar.registration.service;

import com.semparar.registration.model.Sale;
import org.springframework.stereotype.Service;

@Service("saleService")
public interface SaleService {

    Sale saveSale(Sale sale);

    public void delete(Sale sale);


}
