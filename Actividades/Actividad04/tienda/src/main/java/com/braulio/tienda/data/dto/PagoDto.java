package com.braulio.tienda.data.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagoDto implements Serializable{
    private Integer idPagos;
    @NotBlank(message = "Inserta una plataforma de pago")
    private String plataforma;
    private Date fecha;
    private double cargo;
    @NotBlank(message = "Inserta un numero de cuenta")
    private String numCuenta;
    public PagoDto(String plataforma, Date fecha, double cargo, String numCuenta) {
        this.plataforma = plataforma;
        this.fecha = fecha;
        this.cargo = cargo;
        this.numCuenta = numCuenta;
    }

    
}
