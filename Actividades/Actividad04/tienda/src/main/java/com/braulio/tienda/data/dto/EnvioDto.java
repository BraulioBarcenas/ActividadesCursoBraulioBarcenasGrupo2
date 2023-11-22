package com.braulio.tienda.data.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioDto implements Serializable{
    private Integer idEnvio;
    @NotBlank(message = "Ingresa una calle")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String calle;
    @NotBlank(message = "Ingresa una colonia")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String colonia;
    @NotBlank(message = "Ingresa un estado")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String estado;
    @NotBlank(message = "Ingresa una ciudad")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String ciudad;
    @NotNull(message = "Ingresa un numCasa")
    @Positive(message = "Introducir un numero positivo en el numero de casa")
    private Integer numCasa;
    
    public EnvioDto(String calle, String colonia, String estado, String ciudad, Integer numCasa) {
        this.calle = calle;
        this.colonia = colonia;
        this.estado = estado;
        this.ciudad = ciudad;
        this.numCasa = numCasa;
    }

    
}
