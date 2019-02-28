package com.inpivota.omega.FinaleImport;

import com.inpivota.omega.FinaleImports.*;
import com.inpivota.omega.repository.*;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
//@Transactional
public class FinaleImportTest {

    @MockBean
    private ProductCategoryRepository productCategoryRepository;
    @MockBean
    private LocationRepository locationRepository;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private RawProductRepository rawProductRepository;
    @MockBean
    private BomRepository bomRepository;
    @MockBean
    private InventoryItemRepository inventoryItemRepository;

    public FinaleImportTest(){

    }

    @Test
    public void ImportTest() {

        CategoryImport categoryImport = new CategoryImport(productCategoryRepository);
        LocationImport locationImport = new LocationImport(locationRepository);
        ProductImport productImport = new ProductImport(productRepository, rawProductRepository, productCategoryRepository);
        BOMImport bomImport = new BOMImport(productRepository, bomRepository, rawProductRepository);
        InventoryImport inventoryImport = new InventoryImport(productRepository, inventoryItemRepository, rawProductRepository, locationRepository);

        RunImport finaleImport = new RunImport(categoryImport, locationImport, productImport, bomImport, inventoryImport);

        try {
            var results = finaleImport.doImports();

            Assert.assertNotNull(results);
        }catch (Exception e){
            String ex = e.getMessage();
        }
    }
}
