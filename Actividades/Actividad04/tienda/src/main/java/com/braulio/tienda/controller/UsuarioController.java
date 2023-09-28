package com.braulio.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.UsuarioDto;
import com.braulio.tienda.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getUsuarios")
    public List<UsuarioDto> getAllUsers(){
        return usuarioService.getUsuarios();
    }

    @PostMapping("/guardarUsuarios")
    public UsuarioDto saveUser(@RequestBody UsuarioDto dto){
        return usuarioService.guardarUsuario(dto);
    }
}
