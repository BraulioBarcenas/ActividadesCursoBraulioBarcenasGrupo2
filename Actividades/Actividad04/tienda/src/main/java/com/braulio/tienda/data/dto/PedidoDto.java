package com.braulio.tienda.data.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PedidoDto implements Serializable{
    private Integer idPedidos;
    private Date fecha;
    private double total;
    private double iva;
    @NotNull(message = "Debes ingresar un usuario.")
    @Positive(message = "Debe ingresar un usuario valido.")
    private int usuario;
    @NotBlank(message = "Inserta una plataforma de pago")
    private String plataforma;
    @NotBlank(message = "Inserta un numero de cuenta")
    private String numCuenta;
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
}
