package com.braulio.tienda.data.dto;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TiendaDto implements Serializable{
    private Integer idTienda;
    private String nombre;
    private String descripcion;
    private int usuario;

    public TiendaDto(){

    }
}
