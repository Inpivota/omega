package com.inpivota.omega.model.common;

import com.inpivota.omega.enums.RoleName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Override
    public String getUiLabel() {
        return null;
    }
}
