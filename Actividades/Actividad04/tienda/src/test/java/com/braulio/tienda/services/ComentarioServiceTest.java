package com.braulio.tienda.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.braulio.tienda.data.Comentario;
import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.ComentarioDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.repository.ComentarioRepository;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.TiendaRepository;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.service.ComentarioService;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {
    
    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private ComentarioService comentarioService;

    private Usuario usuario;
    private Tienda tienda;
    private Producto producto;
    private Comentario comentario;
    private ComentarioDto comentarioDto;

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

        comentario = new Comentario();
        comentario.setComentario("Esto es un comentario");
        comentario.setFecha(new Date());
        comentario.setIdComentario(1);
        comentario.setProducto(producto);
        comentario.setTienda(tienda);
        comentario.setUsuario(usuario);

        comentarioDto = new ComentarioDto();
        comentarioDto.setComentario("Esto es un comentario");
        comentarioDto.setFecha(new Date());
        comentarioDto.setIdComentario(1);
        comentarioDto.setProducto(producto.getIdProducto());
        comentarioDto.setTienda(tienda.getIdTienda());
        comentarioDto.setUsuario(usuario.getIdUsuario());
        
    }

    @Test
    void crearComentarioShouldReturnComentarioDto(){
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(productoRepository.findById(anyInt())).thenReturn(Optional.of(producto));
        when(tiendaRepository.findById(anyInt())).thenReturn(Optional.of(tienda));
        
        RespuestaGenerica respuesta = comentarioService.crearComentario(comentarioDto);
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVO_COMENTARIO, respuesta.getMensaje());
        
        verify(usuarioRepository).findById(anyInt());
        verify(productoRepository).findById(anyInt());
        verify(tiendaRepository).findById(anyInt());
    }
    
    @Test
    void crearComentarioWithoutUser(){
        when(usuarioRepository.findById(anyInt())).thenThrow(new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));
        assertThrows(EntityNotFoundException.class, ()-> comentarioService.crearComentario(comentarioDto));
    }
    
    @Test
    void obtenerComentariosEnProductoShouldReturnListOfComentario(){
        when(productoRepository.findById(anyInt())).thenReturn(Optional.of(producto));
        
        RespuestaGenerica respuesta = comentarioService.obtenerComentariosEnProducto(producto.getIdProducto());
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_COMENTARIOS_CONSULTADOS, respuesta.getMensaje());
        
        verify(productoRepository).findById(anyInt());
    }

    @Test
    void obtenerComentariosEnProductoWithoutProducto(){
        when(productoRepository.findById(anyInt())).thenThrow(new EntityNotFoundException(Constantes.PRODUCTO_NO_EXISTENTE));
        assertThrows(EntityNotFoundException.class, ()-> comentarioService.obtenerComentariosEnProducto(producto.getIdProducto()));
    }
}
