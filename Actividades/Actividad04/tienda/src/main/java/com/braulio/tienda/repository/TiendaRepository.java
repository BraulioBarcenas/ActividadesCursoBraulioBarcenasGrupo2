package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.Usuario;

import java.util.List;


public interface TiendaRepository extends JpaRepository<Tienda,Integer>{
    List<Tienda> findByUsuario(Usuario usuario);

    List<Tienda> findByNombre(String nombre);
}
