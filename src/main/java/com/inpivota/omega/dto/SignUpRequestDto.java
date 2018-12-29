package com.inpivota.omega.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequestDto {

    @NotBlank
    @Size(min = 4, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 100)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String username;

    @NotBlank
    @Size(max = 200)
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

}
