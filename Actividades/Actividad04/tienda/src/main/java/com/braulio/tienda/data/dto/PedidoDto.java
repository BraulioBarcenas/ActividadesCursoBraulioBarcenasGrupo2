package com.braulio.tienda.data.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PedidoDto implements Serializable{
    private Integer idPedidos;
    private Date fecha;
    private double total;
    private double iva;
    private int usuario;
    private int pago;
    private int envio;
    private String plataforma;
    private String numCuenta;
    private Integer idEnvio;
    private String calle;
    private String colonia;
    private String estado;
    private String ciudad;
    private Integer numCasa;
}
