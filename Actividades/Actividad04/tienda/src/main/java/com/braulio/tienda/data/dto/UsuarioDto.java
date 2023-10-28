package com.braulio.tienda.data.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsuarioDto implements Serializable{

    
    private Integer idUsuario;
    @NotBlank(message = "Ingresa un nombre")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String nombre;
    @NotBlank(message = "Ingresa un apellido paterno")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String apPat;
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String apMat;
    @Email(message = "Ingresa un email valido")
    @NotBlank(message = "Ingresa un email")
    private String email;

    

    public UsuarioDto(){
        
    }
}
