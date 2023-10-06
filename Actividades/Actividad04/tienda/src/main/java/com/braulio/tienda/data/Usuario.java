package com.braulio.tienda.data;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "idUsuario")
    private Integer idUsuario;
    
    @Column(name = "usuNombre", length = 45,nullable = false )
    private String nombre;
    
    @Column(name = "usuApPat", length = 45,nullable = false )
    private String apPat;
    
    @Column(name = "usuApMat", length = 45,nullable = true )
    private String apMat;
    
    @Column(name = "usuEmail", length = 45,nullable = false )
    private String email;
    
    
    
    @Column(name = "usuPassword", length = 255,nullable = false )
    private String password;
}
