package com.braulio.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.PedidoDto;
import com.braulio.tienda.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/nuevoPedido")
    public PedidoDto nuevoPedido(@RequestBody PedidoDto dto){
        return pedidoService.nuevoPedido(dto);
    }

    @GetMapping("/getPedidos")
    public List<PedidoDto> getPedidos(Integer idUsuario){
        return pedidoService.obtenerPedidosPorUsuario(idUsuario);
    }
}
