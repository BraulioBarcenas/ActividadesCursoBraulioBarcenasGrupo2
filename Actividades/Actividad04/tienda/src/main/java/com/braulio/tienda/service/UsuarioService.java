package com.braulio.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.UsuarioDto;
import com.braulio.tienda.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioDto> getUsuarios(){
        List<UsuarioDto> listaUsuarios = new ArrayList<>();

        for (Usuario user : usuarioRepository.findAll()) {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setIdUsuario(user.getIdUsuario());
            usuarioDto.setNombre(user.getNombre());
            usuarioDto.setApPat(user.getApPat());
            usuarioDto.setApMat(user.getApMat());
            usuarioDto.setEmail(user.getEmail());
            usuarioDto.setCalle(user.getCalle());
            usuarioDto.setColonia(user.getColonia());
            usuarioDto.setEstado(user.getEstado());
            usuarioDto.setCiudad(user.getCiudad());
            usuarioDto.setNumCasa(user.getNumCasa());
            listaUsuarios.add(usuarioDto);
        }

        return listaUsuarios;
    }

    public UsuarioDto guardarUsuario(UsuarioDto dto){
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApPat(dto.getApPat());
        usuario.setApMat(dto.getApMat());
        usuario.setEmail(dto.getEmail());
        usuario.setCalle(dto.getCalle());
        usuario.setColonia(dto.getColonia());
        usuario.setEstado(dto.getEstado());
        usuario.setCiudad(dto.getCiudad());
        usuario.setNumCasa(dto.getNumCasa());
        usuario.setPassword(dto.getPassword());
        usuario = usuarioRepository.save(usuario);
        dto.setIdUsuario(usuario.getIdUsuario());
        return dto;
    }
}
