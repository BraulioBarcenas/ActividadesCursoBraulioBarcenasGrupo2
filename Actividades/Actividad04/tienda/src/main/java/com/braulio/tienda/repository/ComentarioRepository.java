package com.braulio.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Comentario;


public interface ComentarioRepository extends JpaRepository<Comentario,Integer>{
    List<Comentario> findByProducto(Integer idComentario);
}
