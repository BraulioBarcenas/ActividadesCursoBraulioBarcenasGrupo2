package com.braulio.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.CarritoDto;
import com.braulio.tienda.data.dto.DetalleCarritoDto;
import com.braulio.tienda.service.CarritoService;

@RestController
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @PostMapping("/addCarrito")
    public DetalleCarritoDto addToCart(@RequestBody CarritoDto carritoDto){
        return carritoService.agregarProductoACarrito(carritoDto);
    }

    @GetMapping("/getCarrito")
    public DetalleCarritoDto getCart(Integer idUsuario){
        return carritoService.obtenerCarrito(idUsuario);
    }
}
