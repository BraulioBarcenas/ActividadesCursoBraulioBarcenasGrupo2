package com.braulio.tienda.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.braulio.tienda.data.dto.CarritoDto;
import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.service.CarritoService;
import com.braulio.tienda.utils.Constantes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class CarritoControllerTest {
    
    @Mock
    private CarritoService carritoService;

    @InjectMocks
    private CarritoController carritoController;

    private CarritoDto carritoDto;
    private ProductoDto productoDto;


    @BeforeEach
    void setUp(){
        carritoDto = new CarritoDto();
        carritoDto.setIdCarrito(1);
        carritoDto.setStock(20);
        carritoDto.setUsuario(1);
        carritoDto.setProducto(1);

        productoDto = new ProductoDto();
    
       productoDto.setIdProducto(1);
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
       productoDto.setTienda(1);
        
    }


    @Test
    void addToCartOk(){
        when(carritoService.agregarProductoACarrito(any(CarritoDto.class))).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(productoDto));
            respuesta.setMensaje(Constantes.EXITO_CARRITO_OBTENIDO);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = carritoController.addToCart(carritoDto).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_CARRITO_OBTENIDO, controllerResponse.getMensaje());

        verify(carritoService).agregarProductoACarrito(any(CarritoDto.class)); 
    }

    @Test
    void getCartOk(){
        when(carritoService.obtenerCarrito(anyInt())).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(productoDto));
            respuesta.setMensaje(Constantes.EXITO_CARRITO_OBTENIDO);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = carritoController.getCart(1).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_CARRITO_OBTENIDO, controllerResponse.getMensaje());

        verify(carritoService).obtenerCarrito(anyInt()); 
    }
}
