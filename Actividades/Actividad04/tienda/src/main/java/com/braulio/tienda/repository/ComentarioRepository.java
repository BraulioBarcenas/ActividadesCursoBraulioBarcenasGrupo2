package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Comentario;


public interface ComentarioRepository extends JpaRepository<Comentario,Integer>{
    
}
