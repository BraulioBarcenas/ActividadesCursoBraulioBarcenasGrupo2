package com.braulio.tienda.data.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDto implements Serializable{
    private Integer idProducto;
    @NotBlank(message = "Ingresa un nombre")
    private String nombre;
    @NotBlank(message = "Ingresa una descripcion")
    private String descripcion;
    @NotBlank(message = "Ingresa un precio")
    private Integer precio;
    private String fechaCaducidad;
    private String marca;
    private String categoria;
    private String color;
    private String talla;
    @NotBlank(message = "Ingresa una ruta de imagen")
    private String img;
    @NotBlank(message = "Ingresa una tienda")
    @Positive(message = "Debes ingresar una tienda valida")
    private int tienda;

    public ProductoDto(){

    }
}
