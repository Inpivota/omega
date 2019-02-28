package com.inpivota.omega.FinaleImports;

import com.inpivota.omega.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

    public static Product FindProductByFinaleId(List<Product> Products, String FinaleId){
        Product result = Products.stream().filter(p -> p.getFinaleId() == FinaleId).findFirst().get();
        return result;
    }

    public static ProductCategory FindCategoryByName(List<ProductCategory> Categories, String Name){
        if(Categories == null || Categories.isEmpty())
            return null;

        var category = Categories.stream().filter(p -> p.getName() == Name).findFirst();
        ProductCategory result = category != null ? category.get() : null;
        return result;
    }

    public static String FindFNSKUBySKU(List<ManageData> Data, String SKU){
        String result = Data.stream().filter(p -> p.SKU == SKU).findFirst().get().fnSKU;
        return result;
    }

    public static RawProduct FindRawProductByFinaleId(List<RawProduct> RawProducts, String FinaleId){
        RawProduct result = RawProducts.stream().filter(p -> p.getFinaleId() == FinaleId).findFirst().get();
        return result;
    }

    public static Bom FindBOMByProductAndRawFinaleId(List<Bom> BOMs, String ProductId, String RawId){
        Bom result = BOMs.stream().filter(p -> p.getProduct().getFinaleId() == ProductId
        && p.getRaw_product().getFinaleId() == RawId).findFirst().get();
        return result;
    }

    public static Location FindLocationByName(List<Location> Locations, String Name){
        Location result = Locations.stream().filter(p -> p.getName() == Name).findFirst().get();
        return result;
    }

    public static InventoryItem FindInventoryItemByProductIdAndLocation(List<InventoryItem> InventoryItems, String ProductId, String LocationName){
        InventoryItem result = InventoryItems.stream().filter(p ->
                (p.getProduct().getFinaleId() == ProductId || p.getRawProduct().getFinaleId() == ProductId)
                        && p.getLocation().getName() == LocationName).findFirst().get();
        return result;
    }

    public static List<ManageData> GetManageData(String filePath, String manageFileName){
        List<ManageData> listManageData = new ArrayList<ManageData>();
        String line = "";
        try{
            var br = new BufferedReader(new FileReader(filePath + manageFileName));
            while  ((line = br.readLine()) != null){
                //String[] data  = line.split("   ");
                String[] data  = line.split("\",\"");
                String sku = data[0];
                String fnSKU = data[1];
                String asin = data[2];
                String productName = data[3];
                int workingQuantity = Integer.parseInt(data[15]);
                int shippedQuantity = Integer.parseInt(data[16]);
                int receivingQuantity = Integer.parseInt(data[17]);
                int totalInboundQuantity = receivingQuantity + shippedQuantity + workingQuantity;

                if (sku != "sku"){
                    listManageData.add(new ManageData(sku, fnSKU, asin, productName, totalInboundQuantity));
                }
            }
        }catch (Exception e){
            //throw new Exception("Error Pulling Data from Manage File" + e.getMessage());
        }
        return listManageData;
    }

    public static List<ManageData> GetFulfillData(String filePath, String fulfillFileName){
        List<ManageData> listManageData = new ArrayList<ManageData>();
        String line = "";
        try{
            var br = new BufferedReader(new FileReader(filePath + fulfillFileName));
            while  ((line = br.readLine()) != null){
                //String[] data  = line.split("   ");
                String[] data  = line.split("   ");
                String sku = data[0];
                int quantity = Integer.parseInt(data[5]);

                if (sku != "sku"){
                    listManageData.add(new ManageData(sku, null, null, null, quantity));
                }
            }
        }catch (Exception e){
            //throw new Exception("Error Pulling Data from Manage File" + e.getMessage());
        }
        return listManageData;
    }

    public static int FindQuantityBySKU(List<ManageData> Data, String SKU){
        int result = Data.stream().filter(p -> p.SKU == SKU).findFirst().get().TotalInbound;
        return result;
    }

    public static Location FindLocaitonByName(List<Location> Locaitons, String Name){
        Location result = Locaitons.stream().filter(p -> p.getName() == Name).findFirst().get();
        return result;
    }
}

