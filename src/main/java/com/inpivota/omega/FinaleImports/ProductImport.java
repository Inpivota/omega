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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        String filePath = "";
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

        if(deleteProductsBeforeInput){
            productRepository.deleteAll();
            rawProductRepository.deleteAll();
        }

        List<ManageData> listManageData = GetManageData(filePath, manageFileName);
        try {
            var br = new BufferedReader(new FileReader(filePath + productFileName));
            while  ((line = br.readLine()) != null){

                String[] data  = line.split(",");

                String finaleProductId = data[0];
                String productName = data[2];

                String productCategory = data[3];
                ProductCategory category = FindCategoryByName(dbCategories, productCategory);

                String SKU = data[5];
                String fnSKU = FindFNSKUBySKU(listManageData, SKU);

                String stringBuilt = data[9];
                int doesGetBuilt = stringBuilt != null ? Integer.parseInt(stringBuilt) : 0;

                String stringDate = data[11];
                Date doneDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(stringDate);

                String notes = data[15];
                String UPC = data[17];

                if (finaleProductId.toLowerCase() != "productid" && finaleProductId != "") {
                    Product dbProduct = FindProductByFinaleId(dbProducts, finaleProductId);

                    if(ImportProducts && (doesGetBuilt > 0 || (doesGetBuilt == 0 &&
                            (productCategory.contains("Sell") || productCategory.contains("Mattress"))))) {
                        try {

                            if (dbProduct == null) {
                                // Add Product to Database
                                Product product = new Product();
                                product.setFinaleId(finaleProductId);
                                product.setCreatedAt(doneDate.toInstant());
                                product.setDescription(notes);
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
                                dbProduct.setName(productName);
                                dbProduct.setProductCategory(category);
                                dbProduct.setDescription(notes);
                                dbProduct.setCreatedAt(doneDate.toInstant());
                                dbProduct.setFnSku(fnSKU);
                                dbProduct.setSku(SKU);
                                // Need to Make sure this persists to the DB
                                updateCount++;
                            }

                        } catch (Exception ex) {
                            errorCount++;
                            errors.add("Had problem with Product: " + productName + ", Id: " + finaleProductId +
                                    ", Error: " + ex.getMessage() + "\r\n");
                        }
                    }

                    if(ImportRawGoods &&
                    doesGetBuilt == 0 && (productCategory == "RAW BULK" || productCategory == "Bags" || productCategory == "Boxes"
                            || productCategory == "Containers" || productCategory == "Envelopes"
                            || productCategory == "Labels" || productCategory == "Office Supplies"
                            || productCategory == "Packaging" || productCategory == "Warehouse Supplies")){

                        RawProduct dbRawProduct = FindRawProductByFinaleId(dbRawProducts, finaleProductId);

                        try{
                            // Create the product
                            if (dbRawProduct == null){

                                RawProduct rawProduct = new RawProduct();

                                rawProduct.setName(productName);
                                rawProduct.setSupplierId(null);
                                rawProduct.setDescription(notes);
                                rawProduct.setNotes("Import Notes");

                                rawProductRepository.save(rawProduct);
                                rawSuccessCount++;
                            }
                            else {
                                // Update the Product

                                dbRawProduct.setName(productName);
                                dbRawProduct.setDescription(notes);
                                dbRawProduct.setSupplierId(null);

                                rawUpdateCount++;
                            }
                        }catch (Exception ex){
                            rawErrorCount++;
                            rawErrors.add("Had problem with Raw Product: " + productName + ", Id: " + finaleProductId +
                                    ", Error: " + ex.getMessage() + "\r\n");
                        }
                    }
                }
            }

        }catch (Exception e) {
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
