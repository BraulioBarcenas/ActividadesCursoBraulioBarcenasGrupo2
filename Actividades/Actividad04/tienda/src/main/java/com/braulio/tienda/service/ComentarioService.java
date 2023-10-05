package com.braulio.tienda.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Comentario;
import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.dto.ComentarioDto;
import com.braulio.tienda.repository.ComentarioRepository;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.TiendaRepository;
import com.braulio.tienda.repository.UsuarioRepository;

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

    public ComentarioDto crearComentario(ComentarioDto comentarioDto){
        Comentario newComentario = new Comentario();
        newComentario.setComentario(comentarioDto.getComentario());
        newComentario.setFecha(new Date());
        newComentario.setProducto(productoRepository.getReferenceById(comentarioDto.getProducto()));
        newComentario.setTienda(tiendaRepository.getReferenceById(comentarioDto.getTienda()));
        newComentario.setUsuario(usuarioRepository.getReferenceById(comentarioDto.getUsuario()));
        comentarioRepository.save(newComentario);
        comentarioDto.setIdComentario(newComentario.getIdComentario());
        return comentarioDto;
    }

    public List<ComentarioDto> obtenerComentariosEnProducto(Integer idProducto){
        List<ComentarioDto> comentariosObtenidos = new ArrayList<>();
        Producto producto = productoRepository.getReferenceById(idProducto);
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
        return comentariosObtenidos;
    }
}
