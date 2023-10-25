package com.braulio.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.data.dto.UsuarioDto;
import com.braulio.tienda.data.dto.UsuarioDtoPass;
import com.braulio.tienda.exceptions.NullParamsException;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.utils.Constantes;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public RespuestaGenerica guardarUsuario(UsuarioDtoPass dto){
        
        RespuestaGenerica respuesta = new RespuestaGenerica();
        Usuario usuario = new Usuario();

        List<Usuario> dupedUsuario = usuarioRepository.findByEmail(dto.getEmail());
        if (!(dupedUsuario == null || dupedUsuario.isEmpty())) {
            throw new NullParamsException(Constantes.EMAIL_DUPLICADO);
        }

        usuario.setNombre(dto.getNombre());
        usuario.setApPat(dto.getApPat());
        usuario.setApMat(dto.getApMat());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario = usuarioRepository.save(usuario);
        dto.setIdUsuario(usuario.getIdUsuario());

        respuesta.setExito(true);
        respuesta.setMensaje(Constantes.EXITO_NUEVO_USUARIO);
        respuesta.getDatos().add(dto);

        return respuesta;
    }
}
