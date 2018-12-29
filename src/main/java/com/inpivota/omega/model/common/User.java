package com.inpivota.omega.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class User extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String email;
    @JsonIgnore
    private String password;

    @ManyToMany
    private List<Role> roles;


    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getUiLabel() {
        return getFullName();
    }
}
