package com.inpivota.omega.FinaleImports;


import com.inpivota.omega.model.ProductCategory;
import com.inpivota.omega.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static com.inpivota.omega.FinaleImports.HelperMethods.FindCategoryByName;


@Service
public class CategoryImport {
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    public CategoryImport(ProductCategoryRepository productCategoryRepository){
        this.productCategoryRepository = productCategoryRepository;
    }

    public String ImportCategories() {

        String filePath = "d:\\FTPTest\\Data\\";
        String productFileName = "ProductCategories.csv";
        String line = "";
        List<ProductCategory> dbCategories = productCategoryRepository.findAll();

        List<String> errors = new ArrayList<>();

        try {
            var br = new BufferedReader(new FileReader(filePath + productFileName));
            while ((line = br.readLine()) != null) {

                try {
                    String category = line;
                    if (!category.equals("Category") && !category.equals("--")) {
                        ProductCategory dbCategory = FindCategoryByName(dbCategories, category);
                        if (dbCategory == null) {
                            ProductCategory newCategory = new ProductCategory();
                            newCategory.setName(category);
                            productCategoryRepository.save(newCategory);
                        }
                    }
                }catch (Exception ex){
                    errors.add(ex.getMessage());
                }

            }

        }catch (Exception e){
            errors.add(e.getMessage());
        }

        if(errors.isEmpty()){
            return "Had no errors importing Categories";
        }
        else {
            return String.join(" ", errors);
        }
    }
}
