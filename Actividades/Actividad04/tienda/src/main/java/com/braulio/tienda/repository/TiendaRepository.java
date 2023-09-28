package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda,Integer>{
    
}
