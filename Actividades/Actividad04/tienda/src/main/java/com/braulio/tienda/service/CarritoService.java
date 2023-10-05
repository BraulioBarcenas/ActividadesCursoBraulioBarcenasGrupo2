package com.braulio.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.DetalleCarrito;
import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.CarritoDto;
import com.braulio.tienda.data.dto.DetalleCarritoDto;
import com.braulio.tienda.repository.CarritoRepository;
import com.braulio.tienda.repository.DetalleCarritoRepository;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.UsuarioRepository;

@Service
public class CarritoService {
    
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public DetalleCarritoDto obtenerCarrito(Integer idUsuario){

        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        Carrito carrito = (carritoRepository.findByUsuario(usuario)).get(0);

        List<DetalleCarrito> productos = detalleCarritoRepository.findByCarritoAndActive(carrito,true);
        DetalleCarritoDto detalleCarritoDto = new DetalleCarritoDto();
        detalleCarritoDto.setCarrito(carrito);
        List<Producto> listaProductos = new ArrayList<>();
        for (DetalleCarrito objetoDetalleCarrito : productos) {
            listaProductos.add(objetoDetalleCarrito.getProducto());
        }
        detalleCarritoDto.setProductos(listaProductos);
        return  detalleCarritoDto;
    }

    public DetalleCarritoDto agregarProductoACarrito(CarritoDto carritoDto){
        Usuario usuario = usuarioRepository.getReferenceById(carritoDto.getUsuario());
        List<Carrito> userCarrito = carritoRepository.findByUsuario(usuario);
        if (userCarrito.size() > 0) {

            DetalleCarrito newDetalleCarrito = new DetalleCarrito();
            newDetalleCarrito.setCarrito(userCarrito.get(0));    
            newDetalleCarrito.setProducto(productoRepository.getReferenceById(carritoDto.getProducto()));    
            newDetalleCarrito.setActive(true);
            detalleCarritoRepository.save(newDetalleCarrito);
        }else{

            // Crear carrito
            Carrito newCarrito = new Carrito();
            newCarrito.setUsuario(usuario);
            carritoRepository.save(newCarrito);

            // Crear DetallesCarrito
            DetalleCarrito newDetalleCarrito = new DetalleCarrito();
            newDetalleCarrito.setCarrito(newCarrito);
            newDetalleCarrito.setProducto(productoRepository.getReferenceById(carritoDto.getProducto()));
            detalleCarritoRepository.save(newDetalleCarrito);

            
        };
        return obtenerCarrito(usuario.getIdUsuario());
    }

}
