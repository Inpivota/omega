package com.inpivota.omega.FinaleImports;

import com.inpivota.omega.model.Location;
import com.inpivota.omega.model.Order;
import com.inpivota.omega.model.OrderLineItem;
import com.inpivota.omega.model.Product;
import com.inpivota.omega.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.inpivota.omega.FinaleImports.HelperMethods.*;

@Service
public class OrderImport {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrderLineItemRepository orderLineItemRepository;
    private LocationRepository locationRepository;

    @Autowired
    public OrderImport(ProductRepository productRepository, OrderRepository orderRepository, OrderLineItemRepository orderLineItemRepository, LocationRepository locationRepository){
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.locationRepository = locationRepository;
    };

    public String ImportOrdersAndLineItems() {

        String fileName = "ProductSales.csv";
        String line = "";
        List<Product> dbProducts = productRepository.findAll();
        List<Location> dbLocations = locationRepository.findAll();
        List<Order> dbOrders = orderRepository.findAll();

        //Counts
        List<String> errors = new ArrayList<>();
        int orderCount = 0;
        int lineItemCount = 0;
        int updateCount = 0;
        int errorCount = 0;
        String resultsToStirng = "";
        List<String> results = new ArrayList<>();

        try {
            var br = new BufferedReader(new FileReader(RunImport.PATH_TO_IMPORT_FILES + fileName));
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String finaleProductId = data[0];

                if(!finaleProductId.equals("--") && !finaleProductId.equals("ProductID")){

                    String locationName = data[4];
                    Optional<Location> dbLocation = FindLocationByName(dbLocations, locationName);

                    String stringOrderDate = data[5];
                    Date orderDate = stringOrderDate.isBlank() ? new Date() : new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(stringOrderDate);
                    String stringShipDate = data[6];
                    Date shipDate = stringShipDate.isBlank() ? new Date() : new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(stringShipDate);

                    String orderId = data[7];
                    String itemPrice = data[8];
                    String stdPrice = data[9];
                    String unitSum = data[10];

                    Optional<Order> order = FindOrderById(dbOrders, orderId);

                    if(order.isEmpty()){

                        Order newOrder = new Order();

                        var newOrderDate = new java.sql.Date(orderDate.getTime());
                        var newShipDate = new java.sql.Date(shipDate.getTime());

                        newOrder.setOrderDate(newOrderDate);
                        newOrder.setShipDate(newShipDate);
                        newOrder.setOrderNumber(orderId);

                        orderRepository.save(newOrder);
                        orderCount++;

                    }else{
                        // I dont think we need to update anything about an order
                    }

                    // Get the Product
                    Optional<Product> dbProduct = FindProductByFinaleId(dbProducts, finaleProductId);
                    if(!dbProduct.isEmpty()){
                        // Get the Order
                        Optional<Order> dbOrder = orderRepository.findByOrderNumber(orderId);
                        if(!dbOrder.isEmpty()) {
                            // Then make sure that this product is not already on the Order
                            OrderLineItem lineItem = orderLineItemRepository.findByProductAndOrder(dbProduct.get(), dbOrder.get());
                            // An order will never contain multiple of the same item on it.
                            if(lineItem == null) {

                                OrderLineItem orderItem = new OrderLineItem();

                                orderItem.setOrder(dbOrder.get());
                                int price = !itemPrice.isEmpty() ? Integer.getInteger(itemPrice) : 0;
                                orderItem.setPrice(price);
                                orderItem.setProduct(dbProduct.get());
                                int quantity = !unitSum.isEmpty() ? Integer.getInteger(unitSum) : 0;
                                orderItem.setQuantity(quantity);
                                orderItem.setLocation(dbLocation.get());

                                orderLineItemRepository.save(orderItem);
                                lineItemCount++;
                            }

                        }else {
                            errorCount++;
                            errors.add("Could Not find Order Id: " + orderId + " in the DB");
                        }
                    }
                }

            }
        } catch (Exception e) {
            errorCount++;
            errors.add(" <||> " + e.getMessage());
        }


        //Adding Results
        results.add("Added " + orderCount + " Orders to the DB");
        results.add("Added " + lineItemCount + " Line Items to the DB");
        //results.add("Updated " + updateCount + " Orders to the DB");
        results.add("Had " + errorCount + " errors \r\n");
        results.addAll(errors);

        resultsToStirng = String.join(" ", results);


        return resultsToStirng;
    }
}