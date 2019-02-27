package com.inpivota.omega.FinaleImport;

import com.inpivota.omega.FinaleImports.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@Transactional
public class FinaleImportTest {

    @Autowired
    private CategoryImport categoryImport;
    @Autowired
    private LocationImport locationImport;
    @Autowired
    private ProductImport productImport;
    @Autowired
    private BOMImport bomImport;
    @Autowired
    private InventoryImport inventoryImport;


    @Test
    public void ImportTest() {

        RunImport finaleImport = new RunImport(categoryImport, locationImport, productImport, bomImport, inventoryImport);

        try {
            var results = finaleImport.doImports();

            Assert.assertNotNull(results);
        }catch (Exception e){
            String ex = e.getMessage();
        }
    }
}
