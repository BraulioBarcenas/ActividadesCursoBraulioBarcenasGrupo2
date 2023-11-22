package com.braulio.tienda.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.data.dto.UsuarioDtoPass;
import com.braulio.tienda.service.UsuarioService;
import com.braulio.tienda.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {
    
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario;
    private UsuarioDtoPass usuarioDtoPass;

    @BeforeEach
    void setUp(){

        usuario = new Usuario();
        
        usuario.setIdUsuario(1);
        usuario.setNombre("Pedro");
        usuario.setApPat("Perez");
        usuario.setApMat("Hernandez");
        usuario.setEmail("pedro.email@gmail.com");
        
        usuarioDtoPass = new UsuarioDtoPass();
        
        usuarioDtoPass.setIdUsuario(1);
        usuarioDtoPass.setNombre("Pedro");
        usuarioDtoPass.setApPat("Perez");
        usuarioDtoPass.setApMat("Hernandez");
        usuarioDtoPass.setEmail("pedro.email@gmail.com");
        usuarioDtoPass.setPassword("Password");
    }

    @Test
    void getAllUsersOk(){
        when(usuarioService.getUsuarios()).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(usuario));
            respuesta.setMensaje(Constantes.EXITO_USUARIOS_CONSULTADOS);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = usuarioController.getAllUsers().getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_USUARIOS_CONSULTADOS, controllerResponse.getMensaje());

        verify(usuarioService).getUsuarios();
    }


    @Test
    void saveUserOk(){
        when(usuarioService.guardarUsuario(any(UsuarioDtoPass.class))).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(usuario));
            respuesta.setMensaje(Constantes.EXITO_NUEVO_USUARIO);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = usuarioController.saveUser(usuarioDtoPass).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVO_USUARIO, controllerResponse.getMensaje());

        verify(usuarioService).guardarUsuario(any(UsuarioDtoPass.class));
    }
}
