package com.braulio.tienda.data.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RespuestaGenericaTest {
 
    private RespuestaGenerica respuestaGenerica;

    @BeforeEach
    public void setUp(){
        respuestaGenerica = new RespuestaGenerica();
    }


    @Test
    public void testDefaultConstructor(){
        assertEquals(0,respuestaGenerica.getCodigo());
        assertFalse(respuestaGenerica.isExito());
        assertNull(respuestaGenerica.getMensaje());
        assertTrue(respuestaGenerica.getDatos().isEmpty());
    }


    @Test
    public void testParametrizedConstructor() {
        RespuestaGenerica respuesta = new RespuestaGenerica(200, true, "exito");
        assertEquals(200, respuesta.getCodigo());
        assertTrue(respuesta.isExito());
        assertEquals("exito", respuesta.getMensaje());
        List<String> listaObjetos = new ArrayList<>();
        listaObjetos.add("Datos");
        respuesta.setDatos(Arrays.asList(listaObjetos.toArray()));
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
    }

    @Test
    public void testSettersAndGetters() {
        respuestaGenerica.setCodigo(404);
        respuestaGenerica.setExito(false);
        respuestaGenerica.setMensaje("No encontrado");
        List<Object> datos = new ArrayList<>();
        datos.add("Dato 1");
        datos.add("Dato 2");
        respuestaGenerica.setDatos(datos);

        assertEquals(404, respuestaGenerica.getCodigo());
        assertFalse(respuestaGenerica.isExito());
        assertEquals("No encontrado", respuestaGenerica.getMensaje());
        assertNotNull(respuestaGenerica.getDatos());
        assertEquals(2, respuestaGenerica.getDatos().size());
    }
}
