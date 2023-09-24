package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer>{
    
}
