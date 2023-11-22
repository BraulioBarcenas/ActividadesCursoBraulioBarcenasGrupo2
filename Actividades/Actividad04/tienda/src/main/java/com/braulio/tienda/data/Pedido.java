package com.braulio.tienda.data;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedidos")
    private Integer idPedidos;

    @Column(name = "pedFecha", nullable = false)
    private Date fecha;
    @Column(name = "pedTotal", nullable = false)
    private double total;
    @Column(name = "pedIVA", nullable = false)
    private double iva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios_idUsuario",nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagos_idPagos", nullable = false)
    private Pago pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "envios_idEnvio", nullable = false)
    private Envio envio;
}
