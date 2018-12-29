package com.inpivota.omega.model;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class FlagCategory extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "flagCategory")
    private List<Flag> flags;

    @Override
    public String getUiLabel() {
        return name;
    }
}
