package com.braulio.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.TiendaDto;
import com.braulio.tienda.repository.TiendaRepository;
import com.braulio.tienda.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TiendaService {
    
    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TiendaDto crearTienda(TiendaDto tiendaDto){
        Usuario usuario = usuarioRepository.findById(tiendaDto.getUsuario())
        .orElseThrow(()-> new EntityNotFoundException("El usuario no existe."));

        List<Tienda> tiendaFound = tiendaRepository.findByUsuario(usuario);
        List<Tienda> tiendaFoundName = tiendaRepository.findByNombre(tiendaDto.getNombre());

        if (!(tiendaFound == null || tiendaFound.isEmpty())) {
            throw new EntityNotFoundException("El usuario ya tiene una tienda");
        }
        
        if (!(tiendaFoundName == null || tiendaFoundName.isEmpty())) {
            throw new EntityNotFoundException("Ya existe una tienda con ese nombre.");
        }


        Tienda newTienda = new Tienda();

        newTienda.setNombre(tiendaDto.getNombre());
        newTienda.setDescripcion(tiendaDto.getDescripcion());
        newTienda.setUsuario(buscarUsuarioPorId(tiendaDto.getUsuario()));
        tiendaRepository.save(newTienda);
        tiendaDto.setIdTienda(newTienda.getIdTienda());
        return tiendaDto;
    }

    private Usuario buscarUsuarioPorId(int idUsuario){
         Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(()-> new EntityNotFoundException("El usuario no existe."));
        return usuario;
    }
}
