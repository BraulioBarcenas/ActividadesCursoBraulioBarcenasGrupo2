package com.braulio.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.CredencialesDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.service.AuthenticationService;
import com.braulio.tienda.service.JwtService;

import jakarta.validation.Valid;


@RestController
public class AuthController {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public ResponseEntity<RespuestaGenerica> authenticate(@Valid @RequestBody CredencialesDto credentials){

        RespuestaGenerica respuesta = authenticationService.getTokenUsuario(credentials);

        HttpStatus status = null;

        if (respuesta.isExito()) {
            status = HttpStatus.OK;
            respuesta.setCodigo(status.value());
        }else{
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }

        return new ResponseEntity<>(respuesta,status);
    }
}
