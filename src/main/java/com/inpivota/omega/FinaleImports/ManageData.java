package com.inpivota.omega.FinaleImports;

public class ManageData{

    ManageData(String SKU, String fnSKU, String ASIN, String ProductName, int TotalInbound)
    {
        this.SKU = SKU;
        this.fnSKU = fnSKU;
        this.ASIN = ASIN;
        this.ProductName = ProductName;
        this.TotalInbound = TotalInbound;
    }
    public String SKU;
    public String fnSKU;
    public String ASIN;
    public String ProductName;
    public int TotalInbound;
}
