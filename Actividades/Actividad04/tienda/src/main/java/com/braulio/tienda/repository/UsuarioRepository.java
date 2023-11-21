package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Usuario;
import java.util.List;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    Optional<Usuario> findByEmail(String email);   
}
