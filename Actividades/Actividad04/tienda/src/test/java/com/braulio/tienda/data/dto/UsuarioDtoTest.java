package com.braulio.tienda.data.dto;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UsuarioDtoTest {
    
    private Validator validator;

    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validUsuarioDtoTest(){
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setIdUsuario(1);
        usuarioDto.setNombre("Pedro");
        usuarioDto.setApPat("Perez");
        usuarioDto.setApMat(null);
        usuarioDto.setEmail("pedro@email.com");
        
        Set<ConstraintViolation<UsuarioDto>> violations = validator.validate(usuarioDto);
        
        assertTrue(violations.isEmpty());
    }
    
    
    @Test
    public void validUsuarioDtoPass(){
        UsuarioDtoPass usuarioDtoPass = new UsuarioDtoPass();
        
        usuarioDtoPass.setIdUsuario(6);
        usuarioDtoPass.setNombre("Juan");
        usuarioDtoPass.setApPat("Mendoza");
        usuarioDtoPass.setApMat("Nuñez");
        usuarioDtoPass.setEmail("si@email.com");
        usuarioDtoPass.setPassword("superpassword");

        Set<ConstraintViolation<UsuarioDto>> violations = validator.validate(usuarioDtoPass);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void invalidUsuarioDtoTest(){
        UsuarioDto usuarioDto = new UsuarioDto();
        
        Set<ConstraintViolation<UsuarioDto>> violations = validator.validate(usuarioDto);
        
        assertEquals(3,violations.size());
        
    }
    
    @Test
    public void invalidUsuarioDtoPassTest(){
        
        UsuarioDtoPass usuarioDtoPass = new UsuarioDtoPass();
        
        Set<ConstraintViolation<UsuarioDto>> violations = validator.validate(usuarioDtoPass);
        assertEquals(4,violations.size());
    }

}
