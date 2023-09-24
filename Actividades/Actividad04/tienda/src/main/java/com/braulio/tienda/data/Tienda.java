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
public class Tienda {
    
    @Id
    @GeneratedValue
    private int idTienda;
    @Column(name = "tienNombre", nullable = false, length = 45)
    private String nombre;
    @Column(name = "tienDescripcion", nullable = false, length = 45)
    private String descripcion;
    @Column(name = "tienRating", nullable = false)
    private float rating;
}
