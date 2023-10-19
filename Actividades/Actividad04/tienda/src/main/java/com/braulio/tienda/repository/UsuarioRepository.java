package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    List<Usuario> findByEmail(String email);   
}
