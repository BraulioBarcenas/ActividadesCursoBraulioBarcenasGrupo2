package com.braulio.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.ComentarioDto;
import com.braulio.tienda.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/nuevoComentario")
    public ComentarioDto newComment(@RequestBody ComentarioDto dto){
        return comentarioService.crearComentario(dto);
    }
    
}
