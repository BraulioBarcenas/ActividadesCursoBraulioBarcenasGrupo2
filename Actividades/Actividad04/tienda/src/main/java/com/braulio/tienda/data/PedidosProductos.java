package com.braulio.tienda.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pedidos_productos")
public class PedidosProductos {

    @Id
    @GeneratedValue
    @Column(name = "idPedidosProductos")
    private Integer idPedidosProductos;

    @MapsId("idPedidos")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedidos_idPedidos", nullable = false)
    private Pedido pedido;

    @MapsId("idProducto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productos_idProducto", nullable = false)
    private Producto producto;

    @Column(name = "prod_pedPrecioDeVenta",nullable = false)
    private Integer precioVenta;
    @Column(name = "prod_pedDescuento", nullable = false)
    private Float descuento;
}
