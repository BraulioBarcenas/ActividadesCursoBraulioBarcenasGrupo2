package com.braulio.tienda.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.data.dto.UsuarioDtoPass;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.service.UsuarioService;
import com.braulio.tienda.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

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
        usuarioDtoPass.setNombre("Pedro");
        usuarioDtoPass.setApPat("Perez");
        usuarioDtoPass.setApMat("Hernandez");
        usuarioDtoPass.setEmail("pedro.email@gmail.com");
        usuarioDtoPass.setPassword("123");
    }   


    @Test
    void getUsuariosShouldReturnListOfUsuarios(){
        // Configurar el mock para retornar una lista de usuarios
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));   

        // Llamar el metodo
        RespuestaGenerica respuesta = usuarioService.getUsuarios();

        // Verificar respuesta
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_USUARIOS_CONSULTADOS, respuesta.getMensaje());
        
        //Verificar la interaccion de los mocks
        verify(usuarioRepository).findAll();
    }


    @Test
    void guardarUsuarioShouldReturnUsuarioDto(){
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$Sg574SOi2EIPbLP3FyMlVOP6etAAq7HOhMzuvaOUNV95ObICuA5iS");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setIdUsuario(1);
            return usuario;
        });
        
        
        RespuestaGenerica respuesta = usuarioService.guardarUsuario(usuarioDtoPass);
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVO_USUARIO, respuesta.getMensaje());

        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void actualizarUsuarioShouldReturnUsuarioDto(){
        usuarioDtoPass.setIdUsuario(1);

        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$Sg574SOi2EIPbLP3FyMlVOP6etAAq7HOhMzuvaOUNV95ObICuA5iS");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setIdUsuario(usuarioDtoPass.getIdUsuario());
            return usuario;
        });
        
        
        RespuestaGenerica respuesta = usuarioService.actualizarUsuario(usuarioDtoPass);
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_USUARIO_ACTUALIZADO, respuesta.getMensaje());

        verify(usuarioRepository).save(any(Usuario.class));
    }
}
