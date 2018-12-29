package com.inpivota.omega.model.common;

import com.inpivota.omega.enums.RoleName;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Role extends BaseEntity {

    private RoleName name;

    @Override
    public String getUiLabel() {
        return null;
    }
}
