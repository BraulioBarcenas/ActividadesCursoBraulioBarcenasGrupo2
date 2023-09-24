package com.braulio.tienda.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tiendas")
public class Producto {
    
    @Id
    @GeneratedValue
    private int idProducto;

    @Column(name = "prodNombre", nullable = false)
    private String nombre;
    @Column(name = "prodDescripcion", nullable = false)
    private String descripcion;
    @Column(name = "prodPrecio", nullable = false)
    private int precio;
    @Column(name = "prodFechaCaducidad", nullable = true)
    private String fechaCaducidad;
    @Column(name = "prodMarca", nullable = true)
    private String marca;
    @Column(name = "prodCategoria", nullable = true)
    private String categoria;
    @Column(name = "prodStock", nullable = false)
    private int stock;
    @Column(name = "prodColor", nullable = true)
    private String color;
    @Column(name = "prodTalla", nullable = true)
    private String talla;
    @Column(name = "prodImg", nullable = false)
    private String img;
    @Column(name = "prodTienda", nullable = false)
    private Tienda tienda;

}
