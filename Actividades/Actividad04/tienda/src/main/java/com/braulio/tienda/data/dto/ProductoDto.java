package com.braulio.tienda.data.dto;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDto implements Serializable{
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private String fechaCaducidad;
    private String marca;
    private String categoria;
    private String color;
    private String talla;
    private String img;
    private int tienda;

    public ProductoDto(){

    }
}
