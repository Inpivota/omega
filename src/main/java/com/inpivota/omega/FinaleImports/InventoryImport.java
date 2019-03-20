package com.inpivota.omega.FinaleImports;

import com.inpivota.omega.model.InventoryItem;
import com.inpivota.omega.model.Location;
import com.inpivota.omega.model.Product;
import com.inpivota.omega.model.RawProduct;
import com.inpivota.omega.repository.InventoryItemRepository;
import com.inpivota.omega.repository.LocationRepository;
import com.inpivota.omega.repository.ProductRepository;
import com.inpivota.omega.repository.RawProductRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.inpivota.omega.FinaleImports.HelperMethods.*;

@Service
public class InventoryImport {

    private ProductRepository productRepository;
    private InventoryItemRepository inventoryItemRepository;
    private RawProductRepository rawProductRepository;
    private LocationRepository locationRepository;

    public InventoryImport(ProductRepository productRepository, InventoryItemRepository inventoryItemRepository,
                           RawProductRepository rawProductRepository, LocationRepository locationRepository) {
        this.productRepository = productRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.rawProductRepository = rawProductRepository;
        this.locationRepository = locationRepository;
    }

    public String ImportProductInventory() {

        String fileName = "Inventory.csv";
        String manageFileName = "Manage.csv";
        String fulfillFileName = "Fulfill.txt";
        String line = "";
        List<Product> dbProducts = productRepository.findAll();
        List<Location> dbLocations = locationRepository.findAll();
        List<RawProduct> dbRawProducts = rawProductRepository.findAll();
        List<InventoryItem> dbInventoryItems = inventoryItemRepository.findAll();

        List<ManageData> shippedInventory = GetManageData(RunImport.PATH_TO_IMPORT_FILES, manageFileName);
        List<ManageData> FBAInventory = GetFulfillData(RunImport.PATH_TO_IMPORT_FILES, fulfillFileName);

        //Counts
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int updateCount = 0;
        int errorCount = 0;
        List<String> results = new ArrayList<>();
        String resultsToStirng = "";

        try {
            var br = new BufferedReader(new FileReader(RunImport.PATH_TO_IMPORT_FILES + fileName));
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String subLocation = data[0];

                if(!subLocation.equals("Sublocation")) {

                    String finaleProductId = data[1];
                    int finaleUnits = Integer.parseInt(data[4]);

                    String newSubLocation = subLocation.equals("--") ? "In Transit" : subLocation;

                    Location dbLocation = FindLocationByName(dbLocations, newSubLocation).orElseThrow();
                    Product dbProduct = FindProductByFinaleId(dbProducts, finaleProductId).orElseThrow();
                    Optional<RawProduct> dbRawProduct = FindRawProductByFinaleId(dbRawProducts, finaleProductId);
                    boolean useRawProduct = dbRawProduct.isPresent();
                    String productSKU = dbProduct.getSku();

                    int shippedUnits = 0;
                    if(subLocation.equals("--")){
                        try {
                            shippedUnits = FindQuantityBySKU(shippedInventory, productSKU);
                        }catch(Exception e) {
                            errors.add("Could Not find Shipped Inventory for Product: " + finaleProductId + "SKU: " + productSKU);
                        }
                    }
                    int FBAUnits = 0;
                    if(subLocation.equals("FBA")){
                        try{
                        FBAUnits = FindQuantityBySKU(FBAInventory, productSKU);
                        }catch(Exception e) {
                            errors.add("Could Not find FBA Inventory for Product: " + finaleProductId + "SKU: " + productSKU);
                        }
                    }
                    int units = subLocation.equals("FBA") ? FBAUnits : subLocation.equals("--") ? shippedUnits : finaleUnits;

                    String productName = useRawProduct ? dbRawProduct.get().getName() : dbProduct.getName();

                    try {

                        Optional<InventoryItem> dbInventoryItem = FindInventoryItemByProductIdAndLocation(dbInventoryItems, finaleProductId, dbLocation);

                        if (!dbInventoryItem.isPresent()) {
                            // Create one
                            InventoryItem newInventoryItem = new InventoryItem();

                            newInventoryItem.setLocation(dbLocation);
                            newInventoryItem.setName(productName + "-" + newSubLocation);
                            newInventoryItem.setProduct(dbProduct);
                            if (useRawProduct) newInventoryItem.setRawProduct(dbRawProduct.get());
                            newInventoryItem.setQuantity(units);

                            inventoryItemRepository.save(newInventoryItem);
                            successCount++;

                        } else {
                            // Update it
                            dbInventoryItem.get().setQuantity(units);

                            updateCount++;
                        }

                    } catch (Exception ex) {
                        errorCount++;
                        errors.add("Had problem with Product Inventory. ProductId: " + finaleProductId + ", Location: " + subLocation +
                                ", Error: " + ex.getMessage() + "\r\n");
                    }
                }
            }
        } catch (Exception e) {
            errorCount++;
            errors.add(" <||> " + e.getMessage());
        }

        //Adding Results
        results.add("Added " + successCount + " Inventory to the DB");
        results.add("Updated " + updateCount + " Inventory to the DB");
        results.add("Had " + errorCount + " errors \r\n");
        results.addAll(errors);

        resultsToStirng = String.join(" ", results);

        return resultsToStirng;
    }
}
