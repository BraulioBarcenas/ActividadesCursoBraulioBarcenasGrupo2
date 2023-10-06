package com.braulio.tienda.data.dto;

import java.io.Serializable;
import java.util.List;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.Producto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleCarritoDto implements Serializable{
    private Carrito carrito;
    @JsonIgnore 
    private List<Producto> productos;
    
    public DetalleCarritoDto(){

    }
}
