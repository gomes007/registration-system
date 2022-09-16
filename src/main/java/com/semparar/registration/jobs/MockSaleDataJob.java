package com.semparar.registration.jobs;

import com.semparar.registration.model.Sale;
import com.semparar.registration.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@Configuration
@Profile("mock-sales")
public class MockSaleDataJob implements InitializingBean {
    @Autowired
    private SaleService saleService;

    @Override
    @Scheduled(fixedRate = 5000L)
    public void afterPropertiesSet() throws Exception {
        var salesQuantity = new Random().nextInt(1, 5);
        log.info("Mockando dados de {} vendas", salesQuantity);

        List<Sale> sales = IntStream
                .rangeClosed(1, salesQuantity)
                .mapToObj(number -> {
                    double quantity = new Random().nextDouble(1.0, 6.0);
                    double price = new Random().nextDouble(1.0, 10.0);
                    return new Sale(
                            null,
                            String.format("Mocked Sale Data - %s", number),
                            quantity,
                            price,
                            null,
                            LocalDateTime.now()
                    );
                }).toList();
        for (Sale sale : sales) {
            this.saleService.saveSale(sale);
        }
    }
}
