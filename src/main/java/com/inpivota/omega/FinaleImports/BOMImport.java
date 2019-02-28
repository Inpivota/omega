package com.inpivota.omega.FinaleImports;

import com.inpivota.omega.model.Bom;
import com.inpivota.omega.model.Product;
import com.inpivota.omega.model.ProductCategory;
import com.inpivota.omega.model.RawProduct;
import com.inpivota.omega.repository.BomRepository;
import com.inpivota.omega.repository.ProductRepository;
import com.inpivota.omega.repository.RawProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.inpivota.omega.FinaleImports.HelperMethods.*;


@Service
public class BOMImport {

    private ProductRepository productRepository;
    private BomRepository bomRepository;
    private RawProductRepository rawProductRepository;

    @Autowired
    public BOMImport (ProductRepository productRepository, BomRepository bomRepository, RawProductRepository rawProductRepository){
        this.productRepository = productRepository;
        this.bomRepository = bomRepository;
        this.rawProductRepository = rawProductRepository;
    };

    public String ImportBOMs(){

        String fileName = "BillOfMaterials.csv";
        String line = "";
        List<Product> dbProducts = productRepository.findAll();
        List<Bom> dbBOMs = bomRepository.findAll();
        List<RawProduct> dbRawProducts = rawProductRepository.findAll();

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

                String finaleProductId = data[0];
                String stringQuantity = data[3];
                BigDecimal quantity = !stringQuantity.equals("") ? new BigDecimal(stringQuantity) : new BigDecimal(0);
                String rawProductId = data[4];
                String itemNote = data[5];

                try {
                    Product dbProduct = FindProductByFinaleId(dbProducts, finaleProductId).orElseThrow();
                    RawProduct dbRawProduct = FindRawProductByFinaleId(dbRawProducts,rawProductId).orElseThrow();

                    if (!finaleProductId.equals("ProductID")) {
                        Optional<Bom> dbBOM = FindBOMByProductAndRawFinaleId(dbBOMs, dbProduct.getFinaleId(), dbRawProduct.getFinaleId());

                        //todo We need a way to delete BOMs from the DB if they are not in the new file...
                        if (!dbBOM.isPresent()){
                            // Add new BOM to db
                            Bom newBOM = new Bom();

                            newBOM.setProduct(dbProduct);
                            newBOM.setRaw_product(dbRawProduct);
                            newBOM.setQuantity(quantity);
                            newBOM.setNote(itemNote);

                            bomRepository.save(newBOM);
                            successCount++;
                        } else {
                            // Update the BOM
                            Bom bom = dbBOM.get();
                            bom.setQuantity(quantity);
                            bom.setNote(itemNote);
                            updateCount++;
                        }
                    }
                }catch (Exception ex){
                    errorCount++;
                    errors.add("Had problem with BOM ProductId: " + finaleProductId + ", RawId: " + rawProductId +
                            ", Error: " + ex.getMessage() + "\r\n");
                }
            }


        }catch (Exception e){
            errorCount++;
            errors.add(" <||> " + e.getMessage());
        }

        //Adding Results
        results.add("Added " + successCount + " BOMs to the DB");
        results.add("Updated " + updateCount + " BOMs to the DB");
        results.add("Had " + errorCount + " errors \r\n");
        results.addAll(errors);

        resultsToStirng = String.join(" ", results);

        return resultsToStirng;
    }
}
