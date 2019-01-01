package com.inpivota.omega.model;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class ProductCategory extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;

    @Override
    public String getUiLabel() {
        return name;
    }
}
