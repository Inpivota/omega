package com.inpivota.omega.FinaleImports;


import com.inpivota.omega.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class RunImport {

    private LocationRepository locationRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ProductRepository productRepository;
    private RawProductRepository rawProductRepository;
    private BomRepository bomRepository;
    private InventoryItemRepository inventoryItemRepository;

    @Scheduled
    public void main() {

        CategoryImport categoryImport = new CategoryImport(productCategoryRepository);
        LocationImport locationImport = new LocationImport(locationRepository);
        ProductImport productImport = new ProductImport(productRepository, rawProductRepository, productCategoryRepository);
        BOMImport bomImport = new BOMImport(productRepository, bomRepository, rawProductRepository);
        InventoryImport inventoryImport = new InventoryImport(productRepository, inventoryItemRepository, rawProductRepository, locationRepository);

        String categoryResults = categoryImport.ImportCategories();
        String locationResults = locationImport.ImportLocations();
        String productResults = productImport.ImportProducts();
        String bomResults = bomImport.ImportBOMs();
        String inventoryResults = inventoryImport.ImportProductInventory();

        // Dump Results somewhere
        String results = categoryResults + "\r\n" + locationResults + "\r\n " + productResults + "\r\n " + bomResults + "\r\n " + inventoryResults;
        System.out.print(results);
    }
}
