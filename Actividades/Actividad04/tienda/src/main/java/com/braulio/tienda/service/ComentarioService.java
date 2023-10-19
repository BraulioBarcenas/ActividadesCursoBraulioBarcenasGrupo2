package com.braulio.tienda.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TiendaRepository tiendaRepository;

    public RespuestaGenerica crearComentario(ComentarioDto comentarioDto){

        Usuario usuario = usuarioRepository.findById(comentarioDto.getUsuario())
            .orElseThrow(()-> new EntityNotFoundException("El usuario no existe."));
        Producto producto = productoRepository.findById(comentarioDto.getProducto())
            .orElseThrow(()-> new EntityNotFoundException("El producto no existe."));
        Tienda tienda = tiendaRepository.findById(comentarioDto.getTienda())
            .orElseThrow(()-> new EntityNotFoundException("La tienda no existe."));

        RespuestaGenerica respuesta = new RespuestaGenerica();

        Comentario newComentario = new Comentario();
        newComentario.setComentario(comentarioDto.getComentario());
        newComentario.setFecha(new Date());
        newComentario.setProducto(producto);
        newComentario.setTienda(tienda);
        newComentario.setUsuario(usuario);
        comentarioRepository.save(newComentario);
        comentarioDto.setIdComentario(newComentario.getIdComentario());
        comentarioDto.setFecha(newComentario.getFecha());

        respuesta.setExito(true);
        respuesta.getDatos().add(comentarioDto);
        respuesta.setMensaje(Constantes.EXITO_NUEVO_COMENTARIO);
        return respuesta;
    }

    public RespuestaGenerica obtenerComentariosEnProducto(Integer idProducto){
        List<ComentarioDto> comentariosObtenidos = new ArrayList<>();

        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(()-> new EntityNotFoundException("El producto no existe."));

        RespuestaGenerica respuesta = new RespuestaGenerica();
        for (Comentario comentarioBD : comentarioRepository.findByProducto(producto)) {
            ComentarioDto comentarioDto = new ComentarioDto();
            comentarioDto.setIdComentario(comentarioBD.getIdComentario());
            comentarioDto.setComentario(comentarioBD.getComentario());
            comentarioDto.setFecha(comentarioBD.getFecha());
            comentarioDto.setProducto(comentarioBD.getProducto().getIdProducto());
            comentarioDto.setTienda(comentarioBD.getTienda().getIdTienda());
            comentarioDto.setUsuario(comentarioBD.getUsuario().getIdUsuario());
            comentariosObtenidos.add(comentarioDto);
        }

        respuesta.setExito(true);
        respuesta.getDatos().add(comentariosObtenidos);
        respuesta.setMensaje(Constantes.EXITO_COMENTARIOS_CONSULTADOS);

        return respuesta;
    }
}
