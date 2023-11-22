package com.braulio.tienda.data.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TiendaDto implements Serializable{
    private Integer idTienda;
    @NotBlank(message = "Ingresa un nombre")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String nombre;
    @NotBlank(message = "Ingresa una descripcion")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String descripcion;
    @NotNull(message = "Debes ingresar un usuario.")
    @Positive(message = "Debe ingresar un usuario valido.")
    private int usuario;

    public TiendaDto(){

    }
}
