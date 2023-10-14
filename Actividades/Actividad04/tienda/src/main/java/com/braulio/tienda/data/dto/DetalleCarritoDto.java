package com.braulio.tienda.data.dto;

import java.io.Serializable;

import com.braulio.tienda.data.Carrito;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleCarritoDto implements Serializable{
    private Carrito carrito;
    private Integer productos;

    public DetalleCarritoDto(){

    }
}
