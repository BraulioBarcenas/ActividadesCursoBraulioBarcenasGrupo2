package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Pedido;
import com.braulio.tienda.data.Usuario;

import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
    List<Pedido> findByUsuario(Usuario usuario);
}
