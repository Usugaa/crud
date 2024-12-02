package com.api.crud.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserDTO {

    @NotBlank(message = "Ingresa tu nombre.")
    private String firstName;

    @NotBlank(message = "Ingresa tu apellido.")
    private String lastName;

    @Email(message = "Email No valido")
    @NotBlank(message = "Ingresa tu email.")
    private String email;
}