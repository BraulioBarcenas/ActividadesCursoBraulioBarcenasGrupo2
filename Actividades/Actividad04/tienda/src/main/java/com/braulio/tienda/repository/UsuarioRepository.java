package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    
}
