package com.braulio.tienda.data.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioDto implements Serializable{
    private Integer idEnvio;
    private String calle;
    private String colonia;
    private String estado;
    private String ciudad;
    private Integer numCasa;
    
    public EnvioDto(String calle, String colonia, String estado, String ciudad, Integer numCasa) {
        this.calle = calle;
        this.colonia = colonia;
        this.estado = estado;
        this.ciudad = ciudad;
        this.numCasa = numCasa;
    }

    
}
