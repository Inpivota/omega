package com.inpivota.omega.model;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Product extends BaseEntity {

    @NaturalId
    @Column(unique = true)
    private String modelNumber;

    private String finaleId;
    private String sku;
    private String name;
    private String description;  // Found that we only need to set the length to 3060, instead of 255. Not sure how to do that here.
    private String notes; // Should probably increase the limit of this field to something bigger than 255.
    //    private int buildOutToDays; //@todo also I don't think that this should go here. This should belong to a bom, probably.
    private String fnSku;
    private String upc;

    // @todo pricing should be a separate concern. a different table
//    private int laborCost;
//    private int itemPrice;
//    private int Cogs;
//    private int totalStdCost; //Not sure if we want a total field. We can just add Labor and Cogs to get the total.

//    @OneToMany(mappedBy = "product")
//    private List<InventoryItem> inventoryItems;

    @ManyToOne
    private ProductCategory productCategory;

    @Override
    public String getUiLabel() {
        return name;
    }

    public Product(){}

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', modelNumber='%s']",
                super.getId(), name, modelNumber);
    }
}
