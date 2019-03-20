package com.inpivota.omega.FinaleImports;

import com.inpivota.omega.model.*;
import org.hibernate.Hibernate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HelperMethods {

    public static Optional<Product> FindProductByFinaleId(List<Product> Products, String FinaleId){
        return Products.stream().filter(p -> p.getFinaleId().equals(FinaleId)).findFirst();
    }

    public static Optional<ProductCategory> FindCategoryByName(List<ProductCategory> Categories, String Name){
        var result = Categories.stream().filter(p -> p.getName().equals(Name)).findFirst();
        return result;
    }

    public static String FindFNSKUBySKU(List<ManageData> Data, String SKU){
        var item = Data.stream().filter(p -> p.SKU.equals(SKU)).findFirst();
        String result = item.isEmpty() ? "" : item.get().fnSKU;
        return result;
    }

    public static Optional<RawProduct> FindRawProductByFinaleId(List<RawProduct> RawProducts, String FinaleId){
        return RawProducts.stream().filter(p -> p.getFinaleId().equals(FinaleId)).findFirst();
    }

    public static Optional<Bom> FindBOMByProductAndRawFinaleId(List<Bom> BOMs, String ProductId, String RawId){
        return BOMs.stream().filter(p -> p.getProduct().getFinaleId().equals(ProductId)
        && p.getRaw_product().getFinaleId().equals(RawId)).findFirst();
    }

    public static Optional<Location> FindLocationByName(List<Location> Locations, String Name){
        return Locations.stream().filter(p -> p.getName().equals(Name)).findFirst();
    }

    public static Optional<InventoryItem> FindInventoryItemByProductIdAndLocation(List<InventoryItem> InventoryItems, String ProductId, Location Location) {

        var result = InventoryItems.stream().filter(p ->
                (p.getProduct().getFinaleId().equals(ProductId) || p.getRawProduct().getFinaleId().equals(ProductId))
                        //&& p.getLocation().getName().equals(LocationName)
                        && p.getLocation().equals(Location)).findFirst();
        return result;
    }

    public static List<ManageData> GetManageData(String filePath, String manageFileName) {
        List<ManageData> listManageData = new ArrayList<ManageData>();
        String line = "";
        try {
            var br = new BufferedReader(new FileReader(filePath + manageFileName));
            while ((line = br.readLine()) != null) {
                //String[] data  = line.split("   ");
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String sku = data[0];
                if (!sku.equals("sku")) {

                    String fnSKU = data[1];
                    String asin = data[2];
                    String productName = data[3];
                    int workingQuantity = Integer.parseInt(data[15]);
                    int shippedQuantity = Integer.parseInt(data[16]);
                    int receivingQuantity = Integer.parseInt(data[17]);
                    int totalInboundQuantity = receivingQuantity + shippedQuantity + workingQuantity;


                    listManageData.add(new ManageData(sku, fnSKU, asin, productName, totalInboundQuantity));
                }
            }
        } catch (Exception e) {
            //throw new Exception("Error Pulling Data from Manage File" + e.getMessage());
        }
        return listManageData;
    }

    public static List<ManageData> GetFulfillData(String filePath, String fulfillFileName) {
        List<ManageData> listManageData = new ArrayList<ManageData>();
        String line = "";
        try {
            var br = new BufferedReader(new FileReader(filePath + fulfillFileName));
            while ((line = br.readLine()) != null) {
                //String[] data  = line.split("   ");
                String[] data = line.split("\t");
                String sku = data[0];
                if (!sku.equals("seller-sku")) {

                    int quantity = Integer.parseInt(data[5]);

                    listManageData.add(new ManageData(sku, null, null, null, quantity));
                }
            }
        } catch (Exception e) {
            //throw new Exception("Error Pulling Data from Manage File" + e.getMessage());
        }
        return listManageData;
    }

    public static int FindQuantityBySKU(List<ManageData> Data, String SKU){
        int result = Data.stream().filter(p -> p.SKU.equals(SKU)).findFirst().get().TotalInbound;
        return result;
    }

    public static Optional<Order> FindOrderById(List<Order> Orders, String OrderId){
        return Orders.stream().filter(p -> p.getOrderNumber().equals(OrderId)).findFirst();
    }
}

