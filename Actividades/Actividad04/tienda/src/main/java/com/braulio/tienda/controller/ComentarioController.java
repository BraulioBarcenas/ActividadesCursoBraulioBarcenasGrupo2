package com.braulio.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.ComentarioDto;
import com.braulio.tienda.service.ComentarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/nuevoComentario")
    public ComentarioDto newComment(@Valid @RequestBody ComentarioDto dto){
        return comentarioService.crearComentario(dto);
    }

    @GetMapping("/obtenerComentarios")
    public List<ComentarioDto> commentsByProduct(Integer idProducto){
        return comentarioService.obtenerComentariosEnProducto(idProducto);
    }
}
