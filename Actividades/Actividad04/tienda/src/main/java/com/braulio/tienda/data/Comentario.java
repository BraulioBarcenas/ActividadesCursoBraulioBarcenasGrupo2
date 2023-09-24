package com.braulio.tienda.data;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comentarios")
public class Comentario {
    
    @Id
    @GeneratedValue
    @Column(name = "idComentario")
    private Integer idComentario;
    private String comentario;
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_idProducto")
    private Producto producto;
    
    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "tiendas_idTienda")
    private Tienda tienda;

    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "usuarios_idUsuario")
    private Usuario usuario;
}
