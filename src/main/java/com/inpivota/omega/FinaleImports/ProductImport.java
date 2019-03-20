package com.inpivota.omega.FinaleImports;

import com.inpivota.omega.model.Product;
import com.inpivota.omega.model.ProductCategory;
import com.inpivota.omega.model.RawProduct;
import com.inpivota.omega.repository.ProductCategoryRepository;
import com.inpivota.omega.repository.ProductRepository;
import com.inpivota.omega.repository.RawProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static com.inpivota.omega.FinaleImports.HelperMethods.*;

@Service
public class ProductImport {

    private ProductRepository productRepository;
    private RawProductRepository rawProductRepository;
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductImport(ProductRepository productRepository, RawProductRepository rawProductRepository, ProductCategoryRepository productCategoryRepository){
        this.productRepository = productRepository;
        this.rawProductRepository = rawProductRepository;
        this.productCategoryRepository = productCategoryRepository;
    };

    public String ImportProducts() {

        boolean ImportProducts = true;
        boolean ImportRawGoods = true;
        boolean deleteProductsBeforeInput = false;

        String productFileName = "Products.csv";
        String manageFileName = "Manage.txt";
        String line = "";
        List<Product> dbProducts = productRepository.findAll();
        List<ProductCategory> dbCategories = productCategoryRepository.findAll();
        List<RawProduct> dbRawProducts = rawProductRepository.findAll();

        //Counts
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int updateCount = 0;
        int errorCount = 0;
        String resultsToStirng = "";
        List<String> results = new ArrayList<>();
        int rawSuccessCount = 0;
        int rawUpdateCount = 0;
        int rawErrorCount = 0;
        List<String> rawErrors = new ArrayList<>();

        // Check Categories in DB

        if (deleteProductsBeforeInput) {
            productRepository.deleteAll();
            rawProductRepository.deleteAll();
        }

        List<ManageData> listManageData = GetManageData(RunImport.PATH_TO_IMPORT_FILES, manageFileName);
        try {
            var br = new BufferedReader(new FileReader(RunImport.PATH_TO_IMPORT_FILES + productFileName));
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String finaleProductId = data[0];
                String productName = data.length > 1 ? data[2] : "";

                if (!finaleProductId.toLowerCase().equals("productid") && !finaleProductId.equals("")) {

                    String productCategory = data.length > 2 ? data[3] : "";
                    ProductCategory category = null;
                    try {
                        category = FindCategoryByName(dbCategories, productCategory).orElseThrow();
                    }catch (Exception ex){
                        errors.add("Error: " + ex.getMessage() + " Product: " + finaleProductId + " Category: " + productCategory);
                    }
                    String SKU = data.length > 4 ? data[5] : "";
                    String fnSKU = FindFNSKUBySKU(listManageData, SKU);

                    String stringBuilt = data.length > 8 ? data[9] : "";
                    int doesGetBuilt = !stringBuilt.isEmpty() ? Integer.parseInt(stringBuilt) : 0;

                    String stringDate = data.length > 10 ? data[11] : "";
                    Date doneDate = stringDate.isBlank() ? new Date() : new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(stringDate);

                    //String notes = data.length > 14 ? data[15] : "";

                    String UPC = data.length > 15 && data.length > 16 ? data[16] : "";

                    Optional<Product> dbProduct = FindProductByFinaleId(dbProducts, finaleProductId);

                    if (ImportProducts && (doesGetBuilt > 0 || (doesGetBuilt == 0 &&
                            (productCategory.contains("Sell") || productCategory.contains("Mattress"))))) {
                        try {

                            if (dbProduct.isEmpty()) {
                                // Add Product to Database
                                Product product = new Product();
                                product.setFinaleId(finaleProductId);
                                product.setCreatedAt(doneDate.toInstant());
                               // product.setDescription(notes);
                                product.setFnSku(fnSKU);
                                product.setName(productName);
                                product.setNotes("Import Note");
                                product.setSku(SKU);
                                product.setUpc(UPC);
                                product.setProductCategory(category);

                                productRepository.save(product);
                                successCount++;
                            } else {
                                // Update existing product
                                /// I would check each value first, if it is different, then I would set the value.  to reduce the number of SQL queries
                                Product product = dbProduct.get();
                                product.setName(productName);
                                product.setProductCategory(category);
                                //product.setDescription(notes);
                                product.setCreatedAt(doneDate.toInstant());
                                product.setFnSku(fnSKU);
                                product.setSku(SKU);
                                product.setProductCategory(category);
                                // Need to Make sure this persists to the DB
                                updateCount++;
                            }

                        } catch (Exception ex) {
                            errorCount++;
                            errors.add("Had problem with Product: " + productName + ", Id: " + finaleProductId +
                                    ", Error: " + ex.getMessage() + "\r\n");
                        }
                    }

                    if (ImportRawGoods &&
                            doesGetBuilt == 0 && (productCategory.equals("RAW BULK") || productCategory.equals("Bags") || productCategory.equals("Boxes")
                            || productCategory.equals("Containers") || productCategory.equals("Envelopes")
                            || productCategory.equals("Labels") || productCategory.equals("Office Supplies")
                            || productCategory.equals("Packaging") || productCategory.equals("Warehouse Supplies"))) {

                        Optional<RawProduct> dbRawProduct = FindRawProductByFinaleId(dbRawProducts, finaleProductId);

                        try {
                            // Create the product
                            if (dbRawProduct.isPresent()) {
                                // Update the Product
                                RawProduct rawProduct = dbRawProduct.get();
                                rawProduct.setName(productName);
                               // rawProduct.setDescription(notes);
                                rawProduct.setSupplierId(null);

                                rawUpdateCount++;
                            } else {
                                RawProduct rawProduct = new RawProduct();
                                rawProduct.setName(productName);
                                rawProduct.setSupplierId(null);
                                rawProduct.setFinaleId(finaleProductId);
                                //rawProduct.setDescription(notes);
                                rawProduct.setNotes("Import Notes");

                                rawProductRepository.save(rawProduct);
                                rawSuccessCount++;
                            }
                        } catch (Exception ex) {
                            rawErrorCount++;
                            rawErrors.add("Had problem with Raw Product: " + productName + ", Id: " + finaleProductId +
                                    ", Error: " + ex.getMessage() + "\r\n");
                        }
                    }
                }
            }

        } catch (Exception e) {
            errorCount++;
            errors.add(" <||> " + e.getMessage());
        }

        //Adding Results
        results.add("Added " + successCount + " products to the DB");
        results.add("Updated " + updateCount + " products to the DB");
        results.add("Had " + errorCount + " errors \r\n");
        results.addAll(errors);

        results.add("Added " + rawSuccessCount + " raw products to the DB");
        results.add("Updated " + rawUpdateCount + " raw products to the DB");
        results.add("Had " + rawErrorCount + " errors \r\n");
        results.addAll(rawErrors);

        resultsToStirng = String.join(" ", results);


        return resultsToStirng;
    }
}
