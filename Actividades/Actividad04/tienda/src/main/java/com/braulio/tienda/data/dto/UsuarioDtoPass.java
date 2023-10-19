package com.braulio.tienda.data.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoPass extends UsuarioDto{
    @NotNull(message = "Ingresa una contrasena")
    @Size(min = 1,max = 255,message = "Introduzca un tamaño correcto de cadena")
    String password;
}
