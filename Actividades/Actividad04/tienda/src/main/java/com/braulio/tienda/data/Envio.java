package com.braulio.tienda.data;

import java.sql.Date;

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
@Table(name = "envios")
public class Envio {
    
    @Id
    @GeneratedValue
    private int idEnvio;
    
    @Column(name = "envEstado", nullable = false, length = 45)
    private String estado;
    @Column(name = "envConductor", nullable = false, length = 45)
    private String conductor;
    @Column(name = "envFechaSalida", nullable = false)
    private Date fechaSalida;
    @Column(name = "envFechaLlegada", nullable = false)
    private Date fechaLlegada;
    @Column(name = "envFirmadoPor", nullable = false, length = 45)
    private String firmadoPor;
    @Column(name = "envCoste", nullable = false)
    private Float coste;
}
