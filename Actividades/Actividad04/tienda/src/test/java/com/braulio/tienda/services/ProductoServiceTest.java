package com.braulio.tienda.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.data.dto.ProductoDtoAddStock;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.exceptions.EntityNotFoundException;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.TiendaRepository;
import com.braulio.tienda.service.ProductoService;
import com.braulio.tienda.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private ProductoService productoService;

    private Usuario usuario;
    private Tienda tienda;
    private Producto producto;
    private ProductoDto productoDto;
    private ProductoDtoAddStock productoDtoAddStock;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        
        usuario.setIdUsuario(1);
        usuario.setNombre("Pedro");
        usuario.setApPat("Perez");
        usuario.setApMat("Hernandez");
        usuario.setEmail("pedro.email@gmail.com");


        tienda = new Tienda();

        tienda.setIdTienda(1);
        tienda.setNombre("Juanchos");
        tienda.setDescripcion("Descripcion");
        tienda.setUsuario(usuario);

        producto = new Producto();

        producto.setIdProducto(1);
        producto.setNombre("Reloj Cuarzo");
        producto.setDescripcion("Reloj con movimiento de cuarzo");
        producto.setPrecio(200);
        producto.setStock(3);
        producto.setFechaCaducidad(null);
        producto.setMarca("Timex");
        producto.setCategoria("Accesorios");
        producto.setColor("Negro");
        producto.setTalla("40mm");
        producto.setImg("/ImagenRelog.png");
        producto.setTienda(tienda);
        
        productoDto = new ProductoDto();

        productoDto.setNombre("Reloj Cuarzo");
        productoDto.setDescripcion("Reloj con movimiento de cuarzo");
        productoDto.setPrecio(200);
        productoDto.setStock(3);
        productoDto.setFechaCaducidad(null);
        productoDto.setMarca("Timex");
        productoDto.setCategoria("Accesorios");
        productoDto.setColor("Negro");
        productoDto.setTalla("40mm");
        productoDto.setImg("/ImagenRelog.png");
        productoDto.setTienda(tienda.getIdTienda());

        productoDtoAddStock = new ProductoDtoAddStock();
        productoDtoAddStock.setIdProducto(producto.getIdProducto());
        productoDtoAddStock.setStock(20);
    }

    @Test
    void nuevoProductoShouldReturnProductoDto(){

        when(tiendaRepository.findById(anyInt())).thenReturn(Optional.of(tienda));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> {
            Producto producto= invocation.getArgument(0);
            producto.setIdProducto(1);
            return producto;
        });
        
        RespuestaGenerica respuesta = productoService.nuevoProducto(productoDto);
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVO_PRODUCTO, respuesta.getMensaje());
        
        verify(productoRepository).save(any(Producto.class));
        verify(tiendaRepository).findById(anyInt());
    }

    @Test 
    void voidnuevoProductoWithoutTienda(){
        when(tiendaRepository.findById(anyInt())).thenThrow(new EntityNotFoundException(Constantes.TIENDA_NO_EXISTENTE));        
        assertThrows(EntityNotFoundException.class, ()-> productoService.nuevoProducto(productoDto));
    }
    
    @Test
    void agregarStockShouldAddStock(){
        when(productoRepository.findById(anyInt())).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> {
            Producto producto= invocation.getArgument(0);
            producto.setIdProducto(1);
            return producto;
        });
        
        RespuestaGenerica respuesta = productoService.agregarStock(productoDtoAddStock);
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_STOCK_ANADIDO, respuesta.getMensaje());
        
        verify(productoRepository).save(any(Producto.class));
    }
    
    @Test
    void agregarStockWithoutProducto(){
        when(productoRepository.findById(anyInt())).thenThrow(new EntityNotFoundException(Constantes.PRODUCTO_NO_EXISTENTE));
        assertThrows(EntityNotFoundException.class, ()-> productoService.agregarStock(productoDtoAddStock));
    }
}
