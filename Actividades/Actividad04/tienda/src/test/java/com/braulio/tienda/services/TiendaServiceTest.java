package com.braulio.tienda.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.data.dto.TiendaDto;
import com.braulio.tienda.repository.TiendaRepository;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.service.TiendaService;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class TiendaServiceTest {
    
    @Mock
    private TiendaRepository tiendaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private TiendaService tiendaService;
    
    private Tienda tienda;
    private TiendaDto tiendaDto;
    private Usuario usuario;

    @BeforeEach
    void setUp(){

        usuario = new Usuario();
        
        usuario.setIdUsuario(1);
        usuario.setNombre("Pedro");
        usuario.setApPat("Perez");
        usuario.setApMat("Hernandez");
        usuario.setEmail("pedro.email@gmail.com");


        tienda = new Tienda();

        tienda.setIdTienda(1);
        tienda.setNombre("Juanchos");
        tienda.setDescripcion("Descripcion");
        tienda.setUsuario(usuario);


        tiendaDto = new TiendaDto();

        tiendaDto.setIdTienda(1);
        tiendaDto.setNombre("Juanchos");
        tiendaDto.setDescripcion("Descripcion");
        tiendaDto.setUsuario(usuario.getIdUsuario());
    }


    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void crearTiendaShouldReturnTiendaDto(){

        when(usuarioRepository.findById(any(Integer.class))).thenReturn(Optional.of(usuario));
        when(tiendaRepository.save(any(Tienda.class))).thenAnswer(invocation -> {
            Tienda tienda = invocation.getArgument(0);
            tienda.setIdTienda(1);
            return tienda;
        });

        RespuestaGenerica respuesta = tiendaService.crearTienda(tiendaDto);

        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVA_TIENDA, respuesta.getMensaje());
        
        verify(tiendaRepository).save(any(Tienda.class));
        verify(usuarioRepository).findById(any(Integer.class));
    }
    
    @Test
    void crearTiendaWithoutUser(){
        when(usuarioRepository.findById(any(Integer.class))).thenThrow(new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));

        assertThrows(EntityNotFoundException.class, ()-> tiendaService.crearTienda(tiendaDto));

    }
}
