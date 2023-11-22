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

import com.braulio.tienda.data.dto.PedidoDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.exceptions.NullParamsException;
import com.braulio.tienda.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@Validated
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/nuevoPedido")
    public ResponseEntity<RespuestaGenerica> nuevoPedido(@Valid @RequestBody PedidoDto dto){
        RespuestaGenerica respuesta = pedidoService.nuevoPedido(dto);
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

    @GetMapping("/getPedidos")
    public ResponseEntity<RespuestaGenerica> getPedidos(Integer idUsuario){
        if (!(idUsuario instanceof Integer) || idUsuario == null) {
            throw new NullParamsException("Especifique un id de usuario");
        }
        RespuestaGenerica respuesta = pedidoService.obtenerPedidosPorUsuario(idUsuario);
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
