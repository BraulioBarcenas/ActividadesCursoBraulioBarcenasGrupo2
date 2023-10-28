package com.braulio.tienda.data.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;


public class EnvioDtoTest {
    

    @Test
    public void testParametrizedConstructor(){
        EnvioDto envioDto = new EnvioDto("Mango", "Frutas", "Guerrero", "Acapulco", 200);
        assertNull(envioDto.getIdEnvio());
        assertNotNull(envioDto.getCalle());
        assertNotNull(envioDto.getColonia());
        assertNotNull(envioDto.getEstado());
        assertNotNull(envioDto.getCiudad());
        assertNotNull(envioDto.getNumCasa());
    }


}
