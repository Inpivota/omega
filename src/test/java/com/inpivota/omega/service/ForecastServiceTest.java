package com.inpivota.omega.service;

import com.inpivota.omega.model.Product;
import com.inpivota.omega.repository.InventoryItemRepository;
import com.inpivota.omega.repository.OrderLineItemRepository;
import com.inpivota.omega.repository.ProductRepository;
import org.junit.Before;
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
    private ProductRepository productRepository;
    @MockBean
    private ForecastService forecastService = new ForecastService(orderLineItemRepository, inventoryItemRepository, productRepository);

    @Before
    private void setup(){
        Product product = new Product();
        product.setSku("fakeSku");
        productRepository.save(product);
    }

    @Test
    public void GetSaleQuantityForDateTest(){

        ForecastService service = forecastService;

        int result = service.GetSaleQuantityForDate("fakeSku", LocalDate.now().minusDays(5));

        assertEquals(1, result);
    }
}