package com.braulio.tienda.data.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoPass extends UsuarioDto{
    @NotNull(message = "Ingresa una contrasena")
    String password;
}
