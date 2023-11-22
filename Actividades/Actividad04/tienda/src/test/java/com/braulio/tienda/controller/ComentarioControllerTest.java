package com.braulio.tienda.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

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
import com.braulio.tienda.service.ComentarioService;
import com.braulio.tienda.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class ComentarioControllerTest {
    
    @Mock
    private ComentarioService comentarioService;
    @InjectMocks
    private ComentarioController comentarioController;

    private ComentarioDto comentarioDto;
    private Comentario comentario;
    private Usuario usuario;
    private Producto producto;
    private Tienda tienda;

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
    void newCommentOk(){
        when(comentarioService.crearComentario(any(ComentarioDto.class))).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(comentario));
            respuesta.setMensaje(Constantes.EXITO_NUEVO_COMENTARIO);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = comentarioController.newComment(comentarioDto).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVO_COMENTARIO, controllerResponse.getMensaje());

        verify(comentarioService).crearComentario(any(ComentarioDto.class));
    }

    @Test
    void commentsByProductOk(){
        when(comentarioService.obtenerComentariosEnProducto(anyInt())).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(comentario));
            respuesta.setMensaje(Constantes.EXITO_COMENTARIOS_CONSULTADOS);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = comentarioController.commentsByProduct(producto.getIdProducto()).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_COMENTARIOS_CONSULTADOS, controllerResponse.getMensaje());

        verify(comentarioService).obtenerComentariosEnProducto(anyInt());
    }
}
