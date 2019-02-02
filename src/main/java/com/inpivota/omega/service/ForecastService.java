package com.inpivota.omega.service;

import com.inpivota.omega.model.OrderLineItem;
import com.inpivota.omega.model.Product;
import com.inpivota.omega.repository.InventoryItemRepository;
import com.inpivota.omega.repository.OrderLineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ForecastService {
    private OrderLineItemRepository orderLineItemRepository;
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    public ForecastService(
            OrderLineItemRepository orderLineItemRepository,
            InventoryItemRepository inventoryItemRepository
    ){
        this.orderLineItemRepository = orderLineItemRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public int GetProductShippedInventory(Product product){
        return inventoryItemRepository.findByProductAndLocationName(product, "In Transit").getQuantity();
    }

    public int GetProductAmazonInventory(Product product){
        return inventoryItemRepository.findByProductAndLocationName(product, "FBA").getQuantity();
    }

    public BigDecimal GetCalculatedAVG(Product product, int days){
        // todo: Write Code to Calculate a Product's AVG
        // Right now it is just returning an average of the last days
        BigDecimal result;
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().minusDays(days);

        List<OrderLineItem> sales = orderLineItemRepository.findallByProductAndOrder_OrderDateBetweenDates(product,startDate, endDate);
        int quantitySold = 0;
        for (OrderLineItem quantity:
                sales) {
            quantitySold += quantity.getQuantity();
        }
        BigDecimal avg = new BigDecimal(quantitySold).divide(new BigDecimal(days));

        // Returning a default of 1 for now for tests. Because we dont have data in DB yet.
        result = avg != new BigDecimal(0) ? avg : new BigDecimal(1);

        // result = calculation of AVG
        //result = avg;
        return result;
    }

    public int GetBuildOutToDays(Product product){
        // todo: Add build out to days to the DB and pull it here
        // default is 31
        return 31;
    }

    public int GetBuildAmount(int buildOutToDays, BigDecimal AVG){
        return new BigDecimal(buildOutToDays).multiply(AVG).intValue();
    }

    public LocalDate GetBuildDate(Product product, BigDecimal AVG){
        int FBA = inventoryItemRepository.findByProductAndLocationName(product, "FBA").getQuantity();
        int shipped = inventoryItemRepository.findByProductAndLocationName(product, "In Transit").getQuantity();
        int daysLeftInStock = new BigDecimal((FBA + shipped)).divide(AVG).intValue();

        return LocalDate.now().plusDays(daysLeftInStock);
    }

    public int GetSaleQuantitiyForDate(Product product,LocalDate date) {
        List<OrderLineItem> orderItems = orderLineItemRepository.findAllByProductAndOrder_OrderDate(product, date);
        int quantitySold = 0;
        for (OrderLineItem quantity:
             orderItems) {
            quantitySold += quantity.getQuantity();
        }
        return quantitySold;
    }
}