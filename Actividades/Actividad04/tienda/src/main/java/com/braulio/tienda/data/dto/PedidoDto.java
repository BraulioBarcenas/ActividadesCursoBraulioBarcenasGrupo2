package com.braulio.tienda.data.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1,max = 255,message = "Introduzca un tamaño correcto de cadena")
    private String plataforma;

    @NotBlank(message = "Inserta un numero de cuenta")
    @Size(min = 1,max = 45,message = "Introduzca un tamaño correcto de cadena")
    private String numCuenta;

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
    @Positive(message = "Introduzca un numero de casa positivo")
    private Integer numCasa;
}
