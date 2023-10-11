package com.braulio.tienda.data.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TiendaDto implements Serializable{
    private Integer idTienda;
    @NotBlank(message = "Ingresa un nombre")
    private String nombre;
    @NotBlank(message = "Ingresa una descripcion")
    private String descripcion;
    @NotNull(message = "Debes ingresar un usuario.")
    @Positive(message = "Debe ingresar un usuario valido.")
    private int usuario;

    public TiendaDto(){

    }
}
