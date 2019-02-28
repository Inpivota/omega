package com.inpivota.omega.FinaleImports;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RunImport {

    static public String PATH_TO_IMPORT_FILES = "./ImportFiles/";

    private CategoryImport categoryImport;
    private LocationImport locationImport;
    private ProductImport productImport;
    private BOMImport bomImport;
    private InventoryImport inventoryImport;

    @Autowired
    public RunImport(
            CategoryImport categoryImport,
            LocationImport locationImport,
            ProductImport productImport,
            BOMImport bomImport,
            InventoryImport inventoryImport
    ){
        this.categoryImport = categoryImport;
        this.locationImport = locationImport;
        this.productImport = productImport;
        this.bomImport = bomImport;
        this.inventoryImport = inventoryImport;
    }

    /**
     * scheduled methods should be void with no args
     **/
    @Scheduled(fixedRate = 900000)
//    @Scheduled(fixedRate = 60000) // For testing only
    public void doImports() {

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
