package com.braulio.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.data.dto.ProductoDtoAddStock;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.TiendaRepository;

import jakarta.persistence.EntityNotFoundException;

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
        newProducto.setStock(productoDto.getStock());
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

    public ProductoDto agregarStock(ProductoDtoAddStock productoDto){

        Producto producto = productoRepository.findById(productoDto.getIdProducto())
        .orElseThrow(()-> new EntityNotFoundException("El producto no existe."));
        ProductoDto returnProductoDto = new ProductoDto();

        producto.setStock(producto.getStock() + productoDto.getStock());
        productoRepository.save(producto);

        returnProductoDto.setTalla(producto.getTalla());
        returnProductoDto.setTienda(producto.getTienda().getIdTienda());
        returnProductoDto.setCategoria(producto.getCategoria());
        returnProductoDto.setColor(producto.getColor());
        returnProductoDto.setDescripcion(producto.getDescripcion());
        returnProductoDto.setFechaCaducidad(producto.getFechaCaducidad());
        returnProductoDto.setIdProducto(producto.getIdProducto());
        returnProductoDto.setImg(producto.getImg());
        returnProductoDto.setMarca(producto.getMarca());
        returnProductoDto.setNombre(producto.getNombre());
        returnProductoDto.setPrecio(producto.getPrecio());
        returnProductoDto.setStock(producto.getStock());

        return returnProductoDto;

        
    }

    private Tienda buscarTiendaPorId(int idTienda){
         Tienda tienda = tiendaRepository.findById(idTienda)
            .orElseThrow(()-> new EntityNotFoundException("La tienda no existe."));
        return tienda;
    }
}
