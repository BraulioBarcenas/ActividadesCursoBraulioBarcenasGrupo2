package com.braulio.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.DetalleCarrito;
import java.util.List;
import com.braulio.tienda.data.Producto;



@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito,Integer>{
    List<DetalleCarrito> findByCarrito(Carrito carrito);
    List<DetalleCarrito> findByCarritoAndActive(Carrito carrito, boolean active);
    List<DetalleCarrito> findByProductoAndActive(Producto producto, boolean active);
}
