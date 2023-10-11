package com.braulio.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.CarritoDto;
import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.service.CarritoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @PostMapping("/addCarrito")
    public List<ProductoDto> addToCart(@Valid @RequestBody CarritoDto carritoDto){
        return carritoService.agregarProductoACarrito(carritoDto);
    }

    @GetMapping("/getCarrito")
    public List<ProductoDto> getCart(Integer idUsuario){
        return carritoService.obtenerCarrito(idUsuario);
    }
}
