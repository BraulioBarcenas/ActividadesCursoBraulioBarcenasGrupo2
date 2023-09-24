package com.braulio.tienda.data;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    private int idComentario;
    private String comentario;
    private Date fecha;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_idProducto")
    private Producto producto;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tiendas_idTienda")
    private Tienda tienda;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios_idUsuario")
    private Usuario usuario;
}
