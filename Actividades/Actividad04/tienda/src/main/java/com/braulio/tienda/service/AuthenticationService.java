package com.braulio.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.CredencialesDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthenticationService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public RespuestaGenerica getTokenUsuario(CredencialesDto credencialesDto){

        RespuestaGenerica respuesta = new RespuestaGenerica();

        Usuario usuarioFound = usuarioRepository.findByEmail(credencialesDto.getEmail()).orElseThrow(()-> new EntityNotFoundException(Constantes.USUARIO_CONTRASENA_INCORRECTA));
        boolean passwordMatches = passwordEncoder.matches(credencialesDto.getPassword(), usuarioFound.getPassword());

        if (passwordMatches) {
            String token = jwtService.generateToken(credencialesDto.getEmail());
            respuesta.setExito(true);
            respuesta.setMensaje(Constantes.EXITO_TOKEN_GENERADO);
            respuesta.getDatos().add(token);
        }else{
            respuesta.setExito(false);
            respuesta.setMensaje(Constantes.USUARIO_CONTRASENA_INCORRECTA);
        }


        return respuesta;
    }
    
}
