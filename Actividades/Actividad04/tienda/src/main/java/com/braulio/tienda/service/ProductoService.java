package com.braulio.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.data.dto.ProductoDtoAddStock;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.TiendaRepository;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TiendaRepository tiendaRepository;
    
    public RespuestaGenerica nuevoProducto(ProductoDto productoDto){
        
        Producto newProducto = new Producto();
        RespuestaGenerica respuesta = new RespuestaGenerica();

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

        respuesta.setExito(true);
        respuesta.getDatos().add(productoDto);
        respuesta.setMensaje(Constantes.EXITO_NUEVO_PRODUCTO);
        return respuesta;
    }

    public RespuestaGenerica agregarStock(ProductoDtoAddStock productoDto){

        Producto producto = productoRepository.findById(productoDto.getIdProducto())
        .orElseThrow(()-> new EntityNotFoundException(Constantes.PRODUCTO_NO_EXISTENTE));
        ProductoDto returnProductoDto = new ProductoDto();
        RespuestaGenerica respuesta = new RespuestaGenerica();

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

        respuesta.setExito(true);
        respuesta.getDatos().add(returnProductoDto);
        respuesta.setMensaje(Constantes.EXITO_STOCK_ANADIDO);
        return respuesta;

        
    }

    private Tienda buscarTiendaPorId(int idTienda){
         Tienda tienda = tiendaRepository.findById(idTienda)
            .orElseThrow(()-> new EntityNotFoundException(Constantes.TIENDA_NO_EXISTENTE));
        return tienda;
    }
}
