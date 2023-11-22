package com.braulio.tienda.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

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
import com.braulio.tienda.service.ProductoService;
import com.braulio.tienda.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {
    
    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    private ProductoDto productoDto;
    private ProductoDtoAddStock productoDtoAddStock;
    private Producto producto;
    private Tienda tienda;
    private Usuario usuario;

    @BeforeEach
    void setUp(){
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
        productoDto.setTienda(1);


        productoDtoAddStock = new ProductoDtoAddStock();

        productoDtoAddStock.setIdProducto(1);
        productoDtoAddStock.setStock(20);

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
    }


    @Test
    void saveProductOk(){
        when(productoService.nuevoProducto(any(ProductoDto.class))).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(producto));
            respuesta.setMensaje(Constantes.EXITO_NUEVO_PRODUCTO);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = productoController.saveProduct(productoDto).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVO_PRODUCTO, controllerResponse.getMensaje());

        verify(productoService).nuevoProducto(any(ProductoDto.class));
    }

    @Test
    void addStockOk(){
        when(productoService.agregarStock(any(ProductoDtoAddStock.class))).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(producto));
            respuesta.setMensaje(Constantes.EXITO_STOCK_ANADIDO);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = productoController.addStock(productoDtoAddStock).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_STOCK_ANADIDO, controllerResponse.getMensaje());

        verify(productoService).agregarStock(any(ProductoDtoAddStock.class));
    }
}
