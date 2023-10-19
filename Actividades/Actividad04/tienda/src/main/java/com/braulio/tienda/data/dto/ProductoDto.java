package com.braulio.tienda.data.dto;

import java.io.Serializable;


import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDto implements Serializable{
    private Integer idProducto;
    
    @NotBlank(message = "Ingresa un nombre")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String nombre;
    
    @NotBlank(message = "Ingresa una descripcion")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String descripcion;
    
    @Positive(message = "El precio debe ser mayor a 0")
    @NotNull(message = "Ingresa un precio")
    private Integer precio;
    
    @NotNull(message = "Tienes que insertar un stock de 1 minimo")
    @Positive(message = "El stock debe ser mayor a 0")
    private Integer stock;
    
    @Nullable
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String fechaCaducidad;
    
    @Nullable
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String marca;
    
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String categoria;
    
    @Nullable
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String color;
    
    @Nullable
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String talla;
    
    @NotBlank(message = "Ingresa una ruta de imagen")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String img;
    
    @NotNull(message = "Ingresa una tienda")
    @Positive(message = "Debes ingresar una tienda valida")
    private int tienda;

    public ProductoDto(){

    }
}
