package com.braulio.tienda.data.dto;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarritoDto implements Serializable{
    private Integer idCarrito;
    private Integer usuario;
    private Integer producto;
}
