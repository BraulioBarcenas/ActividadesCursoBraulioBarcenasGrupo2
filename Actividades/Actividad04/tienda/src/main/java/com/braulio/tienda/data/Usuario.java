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
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column(name = "usuNombre", length = 45,nullable = false )
    private String nombre;
    
    @Column(name = "usuApPat", length = 45,nullable = false )
    private String apPat;
    
    @Column(name = "usuApMat", length = 45,nullable = true )
    private String apMat;
    
    @Column(name = "usuEmail", length = 45,nullable = false )
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_idTienda",nullable = false)
    // @Column(name = "usu_idTienda", nullable = false)
    private Tienda idTienda;
    
    @Column(name = "usuCalle", length = 45,nullable = false )
    private String calle;
    
    @Column(name = "usuColonia", length = 45,nullable = false )
    private String colonia;
    
    @Column(name = "usuEstado", length = 45,nullable = false )
    private String estado;
    
    @Column(name = "usuCiudad", length = 60,nullable = false )
    private String ciudad;
    
    @Column(name = "usuNumCasa", nullable = false)
    private int numCasa;
    
    @Column(name = "usuPassword", length = 255,nullable = false )
    private String password;
}
