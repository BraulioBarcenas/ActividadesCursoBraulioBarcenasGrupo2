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
@Table(name = "envios")
public class Envio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEnvio")
    private Integer idEnvio;
    
    @Column(name = "envCalle", length = 45,nullable = false )
    private String calle;
    
    @Column(name = "envColonia", length = 45,nullable = false )
    private String colonia;
    
    @Column(name = "envEstado", length = 45,nullable = false )
    private String estado;
    
    @Column(name = "envCiudad", length = 60,nullable = false )
    private String ciudad;
    
    @Column(name = "envNumCasa", nullable = false)
    private Integer numCasa;
}
