package com.braulio.tienda.data.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto implements Serializable{
    private Integer idUsuario;
    @NotBlank(message = "Ingresa un nombre")
    private String nombre;
    @NotBlank(message = "Ingresa un apellido paterno")
    private String apPat;
    private String apMat;
    @Email(message = "Ingresa un email valido")
    @NotBlank(message = "Ingresa un email")
    private String email;


    public UsuarioDto(){
        
    }
}
