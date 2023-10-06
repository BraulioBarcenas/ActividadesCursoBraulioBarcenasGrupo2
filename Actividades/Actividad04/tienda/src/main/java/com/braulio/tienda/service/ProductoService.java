package com.braulio.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.TiendaRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TiendaRepository tiendaRepository;
    
    public ProductoDto nuevoProducto(ProductoDto productoDto){
        
        Producto newProducto = new Producto();

        newProducto.setNombre(productoDto.getNombre());
        newProducto.setDescripcion(productoDto.getDescripcion());
        newProducto.setPrecio(productoDto.getPrecio());
        newProducto.setFechaCaducidad(productoDto.getFechaCaducidad());
        newProducto.setMarca(productoDto.getMarca());
        newProducto.setCategoria(productoDto.getCategoria());
        newProducto.setColor(productoDto.getColor());
        newProducto.setTalla(productoDto.getTalla());
        newProducto.setImg(productoDto.getImg());
        newProducto.setTienda(buscarTiendaPorId(productoDto.getTienda()));
        productoRepository.save(newProducto);
        productoDto.setIdProducto(newProducto.getIdProducto());
        return productoDto;
    }

    private Tienda buscarTiendaPorId(int idTienda){
        Tienda tiendaFound = tiendaRepository.getReferenceById(idTienda);
        return tiendaFound;
    }
}
