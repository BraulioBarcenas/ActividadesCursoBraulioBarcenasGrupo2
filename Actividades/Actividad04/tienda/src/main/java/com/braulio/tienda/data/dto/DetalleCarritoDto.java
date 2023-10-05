package com.braulio.tienda.data.dto;

import java.util.List;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.Producto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleCarritoDto {
    private Carrito carrito;
    private List<Producto> productos;
}
