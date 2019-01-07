package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.enums.OperationType;
import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Operation extends BaseEntity {


    private OperationType type;
    private int position;

    @Override
    public String getUiLabel() {
        return null;
    }

    public Operation() {
    }

    public Operation(OperationType type, int position) {
        this.type = type;
        this.position = position;
    }
}
