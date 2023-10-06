package com.braulio.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.DetalleCarrito;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.CarritoDto;
import com.braulio.tienda.data.dto.DetalleCarritoDto;
import com.braulio.tienda.data.dto.ProductoDto;
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

    public List<ProductoDto> obtenerCarrito(Integer idUsuario){

        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        Carrito carrito = (carritoRepository.findByUsuario(usuario)).get(0);

        List<DetalleCarrito> productos = detalleCarritoRepository.findByCarritoAndActive(carrito,true);
        DetalleCarritoDto detalleCarritoDto = new DetalleCarritoDto();
        detalleCarritoDto.setCarrito(carrito);
        List<ProductoDto> listaProductos = new ArrayList<>();
        for (DetalleCarrito objetoDetalleCarrito : productos) {
            ProductoDto productoDto = new ProductoDto();
            productoDto.setIdProducto(objetoDetalleCarrito.getProducto().getIdProducto());
            productoDto.setCategoria(objetoDetalleCarrito.getProducto().getCategoria());
            productoDto.setColor(objetoDetalleCarrito.getProducto().getColor());
            productoDto.setDescripcion(objetoDetalleCarrito.getProducto().getDescripcion());
            productoDto.setFechaCaducidad(objetoDetalleCarrito.getProducto().getFechaCaducidad());
            productoDto.setImg(objetoDetalleCarrito.getProducto().getImg());
            productoDto.setMarca(objetoDetalleCarrito.getProducto().getMarca());
            productoDto.setNombre(objetoDetalleCarrito.getProducto().getNombre());
            productoDto.setPrecio(objetoDetalleCarrito.getProducto().getPrecio());
            productoDto.setTalla(objetoDetalleCarrito.getProducto().getTalla());
            listaProductos.add(productoDto);
        }
        return listaProductos;
    }

    public List<ProductoDto> agregarProductoACarrito(CarritoDto carritoDto){
        Usuario usuario = usuarioRepository.getReferenceById(carritoDto.getUsuario());
        List<Carrito> userCarrito = carritoRepository.findByUsuario(usuario);
        if (userCarrito.size() > 0) {

            DetalleCarrito newDetalleCarrito = new DetalleCarrito();
            newDetalleCarrito.setCarrito(userCarrito.get(0));    
            newDetalleCarrito.setProducto(productoRepository.getReferenceById(carritoDto.getProducto()));    
            newDetalleCarrito.setActive(true);
            detalleCarritoRepository.save(newDetalleCarrito);
            return obtenerCarrito(usuario.getIdUsuario());
        }else{
            
            // Crear carrito
            Carrito newCarrito = new Carrito();
            newCarrito.setUsuario(usuario);
            carritoRepository.save(newCarrito);
            
            // Crear DetallesCarrito
            DetalleCarrito detalleCarrito = new DetalleCarrito();
            detalleCarrito.setCarrito(newCarrito);
            detalleCarrito.setProducto(productoRepository.getReferenceById(carritoDto.getProducto()));
            detalleCarrito.setActive(true);
            detalleCarritoRepository.save(detalleCarrito);

            return obtenerCarrito(usuario.getIdUsuario());
            
        }

    }

}
