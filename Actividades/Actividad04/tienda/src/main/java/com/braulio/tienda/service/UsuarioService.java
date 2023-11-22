package com.braulio.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.data.dto.UsuarioDto;
import com.braulio.tienda.data.dto.UsuarioDtoPass;
import com.braulio.tienda.exceptions.NullParamsException;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RespuestaGenerica getUsuarios(){
        List<UsuarioDto> listaUsuarios = new ArrayList<>();
        RespuestaGenerica respuesta = new RespuestaGenerica();

        for (Usuario user : usuarioRepository.findAll()) {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setIdUsuario(user.getIdUsuario());
            usuarioDto.setNombre(user.getNombre());
            usuarioDto.setApPat(user.getApPat());
            usuarioDto.setApMat(user.getApMat());
            usuarioDto.setEmail(user.getEmail());
            listaUsuarios.add(usuarioDto);
        }

        respuesta.setExito(true);
        respuesta.getDatos().add(listaUsuarios);
        respuesta.setMensaje(Constantes.EXITO_USUARIOS_CONSULTADOS);
        return respuesta;
    }

    public RespuestaGenerica guardarUsuario(@Valid UsuarioDtoPass dto){
        
        RespuestaGenerica respuesta = new RespuestaGenerica();
        Usuario usuario = new Usuario();

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw  new NullParamsException(Constantes.EMAIL_DUPLICADO);
        }

        usuario.setNombre(dto.getNombre());
        usuario.setApPat(dto.getApPat());
        usuario.setApMat(dto.getApMat());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario = usuarioRepository.save(usuario);
        dto.setIdUsuario(usuario.getIdUsuario());

        respuesta.setExito(true);
        respuesta.setMensaje(Constantes.EXITO_NUEVO_USUARIO);
        respuesta.getDatos().add(dto);

        return respuesta;
   }

    public RespuestaGenerica actualizarUsuario(@Valid UsuarioDtoPass dto){
        
        RespuestaGenerica respuesta = new RespuestaGenerica();
        Usuario usuario = new Usuario();

        if (usuarioRepository.findById(dto.getIdUsuario()).isEmpty()) {
            throw  new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE);
        }
        
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw  new NullParamsException(Constantes.EMAIL_DUPLICADO);
        }

        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario = usuarioRepository.save(usuario);
        dto.setIdUsuario(usuario.getIdUsuario());

        respuesta.setExito(true);
        respuesta.setMensaje(Constantes.EXITO_USUARIO_ACTUALIZADO);
        respuesta.getDatos().add(dto);

        return respuesta;
   }
}
