package com.braulio.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.data.dto.TiendaDto;
import com.braulio.tienda.exceptions.DupedEntityException;
import com.braulio.tienda.repository.TiendaRepository;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TiendaService {
    
    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public RespuestaGenerica crearTienda(TiendaDto tiendaDto){
        Usuario usuario = usuarioRepository.findById(tiendaDto.getUsuario())
        .orElseThrow(()-> new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));

        List<Tienda> tiendaFound = tiendaRepository.findByUsuario(usuario);
        List<Tienda> tiendaFoundName = tiendaRepository.findByNombre(tiendaDto.getNombre());

        RespuestaGenerica respuesta = new RespuestaGenerica();

        if (!(tiendaFound == null || tiendaFound.isEmpty())) {
            throw new DupedEntityException(Constantes.USUARIO_CON_TIENDA_YA_EXISTENTE);
        }
        
        if (!(tiendaFoundName == null || tiendaFoundName.isEmpty())) {
            throw new DupedEntityException(Constantes.TIENDA_CON_NOMBRE_YA_EXISTENTE);
        }


        Tienda newTienda = new Tienda();

        newTienda.setNombre(tiendaDto.getNombre());
        newTienda.setDescripcion(tiendaDto.getDescripcion());
        newTienda.setUsuario(usuario);
        tiendaRepository.save(newTienda);
        tiendaDto.setIdTienda(newTienda.getIdTienda());

        respuesta.setExito(true);
        respuesta.getDatos().add(tiendaDto);
        respuesta.setMensaje(Constantes.EXITO_NUEVA_TIENDA);
        return respuesta;
    }

    // private Usuario buscarUsuarioPorId(int idUsuario){
    //      Usuario usuario = usuarioRepository.findById(idUsuario)
    //         .orElseThrow(()-> new DupedEntityException("El usuario no existe."));
    //     return usuario;
    // }
}
