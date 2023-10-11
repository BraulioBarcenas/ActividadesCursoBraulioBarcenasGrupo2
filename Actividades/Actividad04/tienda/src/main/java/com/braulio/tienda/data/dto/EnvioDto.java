package com.braulio.tienda.data.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioDto implements Serializable{
    private Integer idEnvio;
    @NotBlank(message = "Ingresa una calle")
    private String calle;
    @NotBlank(message = "Ingresa una colonia")
    private String colonia;
    @NotBlank(message = "Ingresa un estado")
    private String estado;
    @NotBlank(message = "Ingresa una ciudad")
    private String ciudad;
    @NotBlank(message = "Ingresa un numCasa")
    private Integer numCasa;
    
    public EnvioDto(String calle, String colonia, String estado, String ciudad, Integer numCasa) {
        this.calle = calle;
        this.colonia = colonia;
        this.estado = estado;
        this.ciudad = ciudad;
        this.numCasa = numCasa;
    }

    
}
