package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito,Integer>{
    
}
