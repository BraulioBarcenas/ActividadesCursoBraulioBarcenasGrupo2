package com.braulio.tienda.data;

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
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Integer idProducto;

    @Column(name = "prodNombre", nullable = false)
    private String nombre;
    @Column(name = "prodDescripcion", nullable = false)
    private String descripcion;
    @Column(name = "prodPrecio", nullable = false)
    private Integer precio;
    @Column(name = "prodFechaCaducidad", nullable = true)
    private String fechaCaducidad;
    @Column(name = "prodMarca", nullable = true)
    private String marca;
    @Column(name = "prodCategoria", nullable = true)
    private String categoria;
    @Column(name = "prodColor", nullable = true)
    private String color;
    @Column(name = "prodTalla", nullable = true)
    private String talla;
    @Column(name = "prodImg", nullable = false)
    private String img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tiendas_idTienda")
    private Tienda tienda;

}
