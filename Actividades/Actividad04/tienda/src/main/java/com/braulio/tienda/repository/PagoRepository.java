package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Pago;

public interface PagoRepository extends JpaRepository<Pago,Integer>{
    
}
