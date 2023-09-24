package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
    
}
