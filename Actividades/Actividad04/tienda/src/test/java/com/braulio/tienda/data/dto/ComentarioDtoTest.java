package com.braulio.tienda.data.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ComentarioDtoTest {
    private Validator validator;

    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validComentarioDtoTest(){
        ComentarioDto comentarioDto = new ComentarioDto();

        comentarioDto.setIdComentario(1);
        comentarioDto.setComentario("Esto es un comentario");
        comentarioDto.setFecha(new Date());
        comentarioDto.setProducto(2);
        comentarioDto.setTienda(2);
        comentarioDto.setUsuario(1);
        
        Set<ConstraintViolation<ComentarioDto>> violations = validator.validate(comentarioDto);
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void invalidComentarioDtoTest(){
        ComentarioDto comentarioDto = new ComentarioDto();
        
        Set<ConstraintViolation<ComentarioDto>> violations = validator.validate(comentarioDto);        
        assertEquals(4,violations.size());
    }
}
