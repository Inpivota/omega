package com.inpivota.omega.service;

import com.inpivota.omega.model.Product;
import com.inpivota.omega.repository.InventoryItemRepository;
import com.inpivota.omega.repository.OrderLineItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
//@ContextConfiguration(classes = { ApplicationConfig.class })
public class ForecastServiceTest {
    @MockBean
    private OrderLineItemRepository orderLineItemRepository;
    @MockBean
    private InventoryItemRepository inventoryItemRepository;
    @MockBean
    private ForecastService forecastService = new ForecastService(orderLineItemRepository, inventoryItemRepository);

    @Test
    public void GetSaleQuantityForDateTest(){

        ForecastService service = forecastService;
        Product product = new Product();

        int result = service.GetSaleQuantityForDate(product, LocalDate.now().minusDays(5));

        assertEquals(1, result);
    }
}