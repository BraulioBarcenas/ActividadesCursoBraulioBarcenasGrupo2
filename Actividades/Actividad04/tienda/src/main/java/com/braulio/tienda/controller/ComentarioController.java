package com.braulio.tienda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.ComentarioDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.exceptions.NullParamsException;
import com.braulio.tienda.service.ComentarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
@Validated
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/nuevoComentario")
    public ResponseEntity<RespuestaGenerica> newComment(@Valid @RequestBody ComentarioDto dto){

        RespuestaGenerica respuesta = comentarioService.crearComentario(dto);
                HttpStatus status = null;
        if (respuesta.isExito()) {
            status = HttpStatus.OK;
            respuesta.setCodigo(status.value());
        } else {
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }
        return new ResponseEntity<>(respuesta,status);
    }
    
    @GetMapping("/obtenerComentarios")
    public ResponseEntity<RespuestaGenerica> commentsByProduct(Integer idProducto){
        if (idProducto == null || !(idProducto instanceof Integer)) {
            throw new NullParamsException("Especifique un id de producto");
        }
        RespuestaGenerica respuesta = comentarioService.obtenerComentariosEnProducto(idProducto);
        HttpStatus status = null;
        if (respuesta.isExito()) {
        status = HttpStatus.OK;
        respuesta.setCodigo(status.value());
        } else {
        status = HttpStatus.BAD_REQUEST;
        respuesta.setCodigo(status.value());
        }
        return new ResponseEntity<>(respuesta,status);
    }
}
