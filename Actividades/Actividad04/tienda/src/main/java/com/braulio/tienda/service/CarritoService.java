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
import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.exceptions.OutOfStockException;
import com.braulio.tienda.exceptions.OwnStoreException;
import com.braulio.tienda.repository.CarritoRepository;
import com.braulio.tienda.repository.DetalleCarritoRepository;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

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

    public RespuestaGenerica obtenerCarrito(Integer idUsuario){

        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(()-> new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));
        List<Carrito> carritoFound = carritoRepository.findByUsuario(usuario);
        if (carritoFound.size() < 1 ) {
            throw new EntityNotFoundException(Constantes.CARRITO_NO_DISPONIBLE);
        }
        Carrito carrito = (carritoRepository.findByUsuario(usuario)).get(0);

        RespuestaGenerica respuesta = new RespuestaGenerica();
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
            productoDto.setStock(objetoDetalleCarrito.getStock());
            productoDto.setTienda(objetoDetalleCarrito.getProducto().getTienda().getIdTienda());
            listaProductos.add(productoDto);
        }

        respuesta.setExito(true);
        respuesta.setMensaje(Constantes.EXITO_CARRITO_OBTENIDO);
        respuesta.getDatos().add(listaProductos);
        return respuesta;
    }

    public RespuestaGenerica agregarProductoACarrito(CarritoDto carritoDto){
        Usuario usuario = usuarioRepository.findById(carritoDto.getUsuario())
            .orElseThrow(()-> new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));;
        List<Carrito> userCarrito = carritoRepository.findByUsuario(usuario);


        // Tiene carrito
        if (userCarrito.size() > 0) {
            Producto producto = productoRepository.findById(carritoDto.getProducto())
            .orElseThrow(()-> new EntityNotFoundException(Constantes.PRODUCTO_NO_EXISTENTE));

            if (producto.getTienda().getUsuario().equals(usuario)) {
                throw new OwnStoreException(Constantes.COMPRA_DESDE_MISMA_TIENDA);
            }

            List<DetalleCarrito> productoInCarrito = detalleCarritoRepository.findByProductoAndActive(producto, true);

            if (!(productoInCarrito == null || productoInCarrito.isEmpty())) {
                Integer stockARevisar = carritoDto.getStock() + productoInCarrito.get(0).getStock();
                Boolean disponibleAComprar = revisarStock(producto.getIdProducto(), stockARevisar);

                if (disponibleAComprar) {

                    productoInCarrito.get(0).setStock(stockARevisar);
                    detalleCarritoRepository.save(productoInCarrito.get(0));
                }else{
                    throw new OutOfStockException(Constantes.STOCK_EXCEDENTE);
                }



                                
            }else{
                DetalleCarrito newDetalleCarrito = new DetalleCarrito();
                
    
                newDetalleCarrito.setCarrito(userCarrito.get(0));    
                newDetalleCarrito.setProducto(producto); 
                newDetalleCarrito.setActive(true);
                newDetalleCarrito.setStock(carritoDto.getStock());
                detalleCarritoRepository.save(newDetalleCarrito);
            }
            
            return obtenerCarrito(usuario.getIdUsuario());

        // Creación de carrito
        }else{
            Producto producto = productoRepository.findById(carritoDto.getProducto())
            .orElseThrow(()-> new EntityNotFoundException(Constantes.PRODUCTO_NO_EXISTENTE));
            if (producto.getTienda().getUsuario().equals(usuario)) {
                throw new OwnStoreException(Constantes.COMPRA_DESDE_MISMA_TIENDA);
            }
            Integer stockARevisar = carritoDto.getStock();
            Boolean disponibleAComprar = revisarStock(producto.getIdProducto(), stockARevisar);
            
            if (disponibleAComprar) {
            
                // Crear carrito
                Carrito newCarrito = new Carrito();
                newCarrito.setUsuario(usuario);
                carritoRepository.save(newCarrito);
                
                // Crear DetallesCarrito
                DetalleCarrito detalleCarrito = new DetalleCarrito();
                detalleCarrito.setCarrito(newCarrito);
                detalleCarrito.setProducto(producto);
                detalleCarrito.setStock(carritoDto.getStock());
                detalleCarrito.setActive(true);
                detalleCarritoRepository.save(detalleCarrito);
                
                return obtenerCarrito(usuario.getIdUsuario());
            }else{
                throw new OutOfStockException(Constantes.STOCK_EXCEDENTE);
            }
            
        }

    }

    public boolean revisarStock(Integer idProducto, Integer StockARevisar){
        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(()-> new EntityNotFoundException(Constantes.PRODUCTO_SIN_STOCK_O_INEXISTENTE));

        if (StockARevisar > producto.getStock()) {
            return false;
        }else{
            return true;
        }
    }

}
