package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.Usuario;

import java.util.List;


public interface CarritoRepository extends JpaRepository<Carrito,Integer>{
    List<Carrito> findByUsuario(Usuario usuario);
}
