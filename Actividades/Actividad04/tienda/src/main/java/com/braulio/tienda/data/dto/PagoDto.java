package com.braulio.tienda.data.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagoDto implements Serializable{
    private Integer idPagos;
    private String plataforma;
    private Date fecha;
    private double cargo;
    private String numCuenta;
    public PagoDto(String plataforma, Date fecha, double cargo, String numCuenta) {
        this.plataforma = plataforma;
        this.fecha = fecha;
        this.cargo = cargo;
        this.numCuenta = numCuenta;
    }

    
}
