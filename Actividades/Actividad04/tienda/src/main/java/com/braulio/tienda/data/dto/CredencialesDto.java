package com.braulio.tienda.data.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredencialesDto {
    
    @NotNull(message = "Debe ingresar un correo valido")
    private String email;
    @NotNull(message = "Debe ingresar una contraseña valida")
    private String password;
}
