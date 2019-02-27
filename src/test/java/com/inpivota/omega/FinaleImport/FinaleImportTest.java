package com.inpivota.omega.FinaleImport;

import com.inpivota.omega.FinaleImports.RunImport;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@Transactional
public class FinaleImportTest {

    @Test
    public void ImportTest() {

        RunImport finaleImport = new RunImport();

        try {
            var results = finaleImport.Import();

            Assert.assertNotNull(results);
        }catch (Exception e){
            String ex = e.getMessage();
        }
    }
}
