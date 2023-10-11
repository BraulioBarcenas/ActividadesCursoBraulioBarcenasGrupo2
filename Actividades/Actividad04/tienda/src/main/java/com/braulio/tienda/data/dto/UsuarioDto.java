package com.braulio.tienda.data.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto implements Serializable{
    Integer idUsuario;
    @NotBlank(message = "Ingresa un nombre")
    String nombre;
    @NotBlank(message = "Ingresa un apellido paterno")
    String apPat;
    String apMat;
    @Email
    @NotBlank(message = "Ingresa un email")
    String email;
    @NotNull(message = "Ingresa una contrasena")
    String password;

    public UsuarioDto(){
        
    }
}
