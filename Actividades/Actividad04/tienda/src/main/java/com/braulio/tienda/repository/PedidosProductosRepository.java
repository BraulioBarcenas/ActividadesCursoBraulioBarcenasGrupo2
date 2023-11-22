package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.braulio.tienda.data.PedidosProductos;

@Repository
public interface PedidosProductosRepository extends JpaRepository<PedidosProductos,Integer>{
    
}
