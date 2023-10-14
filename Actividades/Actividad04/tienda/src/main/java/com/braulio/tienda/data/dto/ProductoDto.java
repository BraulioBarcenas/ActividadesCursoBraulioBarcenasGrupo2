package com.braulio.tienda.data.dto;

import java.io.Serializable;


import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Positive
    @NotNull(message = "Ingresa un precio")
    private Integer precio;
    @NotNull(message = "Tienes que insertar un stock de 1 minimo")
    @Positive
    private Integer stock;
    @Nullable
    private String fechaCaducidad;
    @Nullable
    private String marca;
    private String categoria;
    @Nullable
    private String color;
    @Nullable
    private String talla;
    @NotBlank(message = "Ingresa una ruta de imagen")
    private String img;
    @NotNull(message = "Ingresa una tienda")
    @Positive(message = "Debes ingresar una tienda valida")
    private int tienda;

    public ProductoDto(){

    }
}
