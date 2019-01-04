package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.enums.OperationType;
import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Operation extends BaseEntity {


    private OperationType type;

    @Override
    public String getUiLabel() {
        return null;
    }
}
