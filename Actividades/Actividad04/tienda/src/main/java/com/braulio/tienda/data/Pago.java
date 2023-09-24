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
@Table(name = "pagos")
public class Pago {
    
    @Id
    @GeneratedValue
    @Column(name = "idPago")
    private Integer idPago;

    @Column(name = "pagoPlataforma", nullable = false, length = 255)
    private String plataforma;
    @Column(name = "pagoFecha", nullable = false)
    private Date fecha;
    @Column(name = "pagoCargo", nullable = false)
    private Float cargo;
    @Column(name = "pagoNumCuenta", nullable = false, length = 45)
    private String numCuenta;
}
