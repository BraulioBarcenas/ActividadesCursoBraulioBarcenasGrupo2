package com.braulio.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braulio.tienda.data.Comentario;
import com.braulio.tienda.data.Producto;


public interface ComentarioRepository extends JpaRepository<Comentario,Integer>{
    List<Comentario> findByProducto(Producto producto);
}
