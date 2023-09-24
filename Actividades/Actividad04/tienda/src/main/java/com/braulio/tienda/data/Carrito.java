package com.braulio.tienda.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "carrito")
public class Carrito {
    
    @Id
    @GeneratedValue
    private int idCarrito;

    @OneToOne(fetch = FetchType.LAZY,optional =  false)
    @JoinColumn(name = "usuarios_idUsuario")
    private Usuario usuario;

    @Column(name = "carrTotal", nullable = false)
    private Float total;
}
