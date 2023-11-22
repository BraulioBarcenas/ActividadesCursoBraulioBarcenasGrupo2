package com.braulio.tienda.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.DetalleCarrito;
import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.CarritoDto;
import com.braulio.tienda.data.dto.DetalleCarritoDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.repository.CarritoRepository;
import com.braulio.tienda.repository.DetalleCarritoRepository;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.service.CarritoService;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CarritoServiceTest {
    
    @Mock
    private CarritoRepository carritoRepository;
    @Mock
    private DetalleCarritoRepository detalleCarritoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private CarritoService carritoService;

    private Usuario usuario;
    private Producto producto;
    private Tienda tienda;
    private Carrito carrito;
    private CarritoDto carritoDto;
    private DetalleCarrito detalleCarrito;
    private DetalleCarritoDto detalleCarritoDto;

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

        carrito = new Carrito();
        carrito.setIdCarrito(1);
        carrito.setUsuario(usuario);

        carritoDto = new CarritoDto();
        carritoDto.setStock(23);
        carritoDto.setUsuario(usuario.getIdUsuario());
        carritoDto.setProducto(producto.getIdProducto());
        
        detalleCarrito = new DetalleCarrito();
        detalleCarrito.setActive(true);
        detalleCarrito.setCarrito(carrito);
        detalleCarrito.setIdDetalleCarrito(1);
        detalleCarrito.setProducto(producto);
        detalleCarrito.setStock(3);

        detalleCarritoDto = new DetalleCarritoDto();
        detalleCarritoDto.setCarrito(carrito);
        detalleCarritoDto.setProductos(producto.getIdProducto());
    }


    @Test
    void obtenerCarritoShouldReturnListOfProductos(){
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(carritoRepository.findByUsuario(any(Usuario.class))).thenReturn(Arrays.asList(carrito));
        when(detalleCarritoRepository.findByCarritoAndActive(any(Carrito.class),anyBoolean())).thenReturn(Arrays.asList(detalleCarrito));
        
        RespuestaGenerica respuesta = carritoService.obtenerCarrito(usuario.getIdUsuario());
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_CARRITO_OBTENIDO, respuesta.getMensaje());
        
        verify(usuarioRepository).findById(anyInt());
        verify(detalleCarritoRepository).findByCarritoAndActive(any(Carrito.class), anyBoolean());
        verify(carritoRepository, times(2)).findByUsuario(any(Usuario.class));
    }
    
    @Test
    void obtenerCarritoWithoutUsuario(){
        when(usuarioRepository.findById(anyInt())).thenThrow(new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));
        
        assertThrows(EntityNotFoundException.class, ()-> carritoService.obtenerCarrito(usuario.getIdUsuario()));
    }
    
    
    @Test
    void agregarProductoACarritoShouldReturnCarritoDto(){
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(detalleCarritoRepository.findByCarritoAndActive(any(Carrito.class),anyBoolean())).thenReturn(Arrays.asList(detalleCarrito));
        when(carritoRepository.findByUsuario(any(Usuario.class))).thenReturn(Arrays.asList(carrito));
        when(productoRepository.findById(anyInt())).thenReturn(Optional.of(producto));
        when(detalleCarritoRepository.save(any(DetalleCarrito.class))).thenAnswer(invocation -> {
            DetalleCarrito detalleCarrito = invocation.getArgument(0);
            detalleCarrito.setIdDetalleCarrito(1);
            return detalleCarrito;
        });
        
        RespuestaGenerica respuesta = carritoService.agregarProductoACarrito(carritoDto);
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_CARRITO_OBTENIDO, respuesta.getMensaje());
        
        verify(usuarioRepository, times(2)).findById(anyInt());
        verify(detalleCarritoRepository).findByCarritoAndActive(any(Carrito.class), anyBoolean());
        verify(carritoRepository, times(3)).findByUsuario(any(Usuario.class));
        verify(productoRepository).findById(anyInt());
        verify(detalleCarritoRepository).save(any(DetalleCarrito.class));
    }


    @Test
    void agregarProductoACarritoWithoutUser(){
        when(usuarioRepository.findById(anyInt())).thenThrow(new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));
        assertThrows(EntityNotFoundException.class, ()-> carritoService.obtenerCarrito(usuario.getIdUsuario()));
    }
}
