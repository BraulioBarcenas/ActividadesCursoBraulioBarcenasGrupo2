package com.braulio.tienda.data.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDtoAddStock {
    @NotNull(message = "Ingresa el id del producto a añadir stock")
    private Integer idProducto;
    @NotNull(message = "Tienes que insertar un stock de 1 minimo")
    @Positive(message = "El stock debe ser mayor a 0")
    private Integer stock;
}
