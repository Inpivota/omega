package com.inpivota.omega.service;

import com.inpivota.omega.model.OrderLineItem;
import com.inpivota.omega.model.Product;
import com.inpivota.omega.repository.InventoryItemRepository;
import com.inpivota.omega.repository.OrderLineItemRepository;
import com.inpivota.omega.repository.ProductRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class ForecastService {
    private OrderLineItemRepository orderLineItemRepository;
    private InventoryItemRepository inventoryItemRepository;
    private ProductRepository productRepository;

    @Autowired
    public ForecastService(
            OrderLineItemRepository orderLineItemRepository,
            InventoryItemRepository inventoryItemRepository,
            ProductRepository productRepository
    ){
        this.orderLineItemRepository = orderLineItemRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getProductsForForecasting(){
        return productRepository.findAll();
    }

    public int GetProductShippedInventory(@GraphQLContext Product product){
        return inventoryItemRepository.findByProductAndLocationName(product, "In Transit").getQuantity();
    }

    public int GetProductAmazonInventory(@GraphQLContext Product product){
        return inventoryItemRepository.findByProductAndLocationName(product, "FBA").getQuantity();
    }

    public BigDecimal GetCalculatedAVG(@GraphQLArgument(name = "productSku") String sku, @GraphQLArgument(name = "days") int days){
        Product product = productRepository.findBySku(sku).orElseThrow();
        // todo: Write Code to Calculate a Product's AVG
        // Right now it is just returning an average of the last days
        BigDecimal result;
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().minusDays(days);

        List<OrderLineItem> sales = orderLineItemRepository.findAllByProductAndOrder_OrderDateBetween(product, startDate, endDate);
        int quantitySold = 0;
        for (OrderLineItem quantity:
                sales) {
            quantitySold += quantity.getQuantity();
        }
        BigDecimal avg = new BigDecimal(0);
        if(quantitySold > 0) {
            avg = new BigDecimal(quantitySold).divide(new BigDecimal(days));
        }
        result = avg;

        return result;
    }

    public int GetBuildOutToDays(@GraphQLContext Product product){
        // todo: Add build out to days to the DB and pull it here
        // default is 31
        return 31;
    }

    public int GetBuildAmount(@GraphQLArgument(name = "buildOutToDays") int buildOutToDays, @GraphQLArgument(name = "avg") BigDecimal avg){
        return new BigDecimal(buildOutToDays).multiply(avg).intValue();
    }

    public LocalDate GetBuildDate(@GraphQLArgument(name = "productSku") String sku, @GraphQLArgument(name = "avg") BigDecimal avg){
        Product product = productRepository.findBySku(sku).orElseThrow();
        int FBA = inventoryItemRepository.findByProductAndLocationName(product, "FBA").getQuantity();
        int shipped = inventoryItemRepository.findByProductAndLocationName(product, "In Transit").getQuantity();
        int daysLeftInStock = new BigDecimal((FBA + shipped)).divide(avg, RoundingMode.HALF_UP).intValue();

        return LocalDate.now().plusDays(daysLeftInStock);
    }

    public int GetSaleQuantityForDate(@GraphQLArgument(name = "productSku") String sku, LocalDate date) {
        Product product = productRepository.findBySku(sku).orElseThrow();
        List<OrderLineItem> orderItems = orderLineItemRepository.findAllByProductAndOrder_OrderDate(product, date);
        int quantitySold = 0;
        for (OrderLineItem quantity:
             orderItems) {
            quantitySold += quantity.getQuantity();
        }
        return quantitySold;
    }
}