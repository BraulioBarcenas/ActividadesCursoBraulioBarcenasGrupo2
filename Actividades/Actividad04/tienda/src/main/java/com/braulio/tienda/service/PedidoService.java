package com.braulio.tienda.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.DetalleCarrito;
import com.braulio.tienda.data.Envio;
import com.braulio.tienda.data.Pago;
import com.braulio.tienda.data.Pedido;
import com.braulio.tienda.data.PedidosProductos;
import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.EnvioDto;
import com.braulio.tienda.data.dto.PagoDto;
import com.braulio.tienda.data.dto.PedidoDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.exceptions.OwnStoreException;
import com.braulio.tienda.repository.CarritoRepository;
import com.braulio.tienda.repository.DetalleCarritoRepository;
import com.braulio.tienda.repository.EnvioRepository;
import com.braulio.tienda.repository.PagoRepository;
import com.braulio.tienda.repository.PedidoRepository;
import com.braulio.tienda.repository.PedidosProductosRepository;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private EnvioRepository envioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;
    @Autowired
    private PedidosProductosRepository pedidosProductosRepository;

    public Integer crearPago(PagoDto pagoDto){
        Pago pago = new Pago();

        pago.setCargo(pagoDto.getCargo());
        pago.setFecha(pagoDto.getFecha());
        pago.setNumCuenta(pagoDto.getNumCuenta());
        pago.setPlataforma(pagoDto.getPlataforma());
        pagoRepository.save(pago);
        
        return pago.getIdPagos();
    }

    public Integer crearEnvio(EnvioDto envioDto){
        Envio envio = new Envio();
        envio.setCalle(envioDto.getCalle());
        envio.setCiudad(envioDto.getCiudad());
        envio.setColonia(envioDto.getColonia());
        envio.setEstado(envioDto.getEstado());
        envio.setNumCasa(envioDto.getNumCasa());
        envioRepository.save(envio);
        return envio.getIdEnvio();
    }


    public RespuestaGenerica nuevoPedido(PedidoDto pedidoDto){
        double IVA = 0.16;
        Usuario usuario = usuarioRepository.findById(pedidoDto.getUsuario())
            .orElseThrow(()-> new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));

        RespuestaGenerica respuesta = new RespuestaGenerica();

        
        Pedido newPedido = new Pedido();
        newPedido.setFecha(new Date());
        newPedido.setUsuario(usuario);
        Integer total = 0;
        List<Carrito> carrito = carritoRepository.findByUsuario(usuario);
        if (carrito == null || carrito.isEmpty()) {
            throw new EntityNotFoundException(Constantes.CARRITO_NO_DISPONIBLE);
        }

        List<DetalleCarrito> productos = detalleCarritoRepository.findByCarritoAndActive(carrito.get(0),true);
        if (productos == null || productos.isEmpty()) {
            throw new EntityNotFoundException(Constantes.CARRITO_NO_DISPONIBLE);
        }

        for (DetalleCarrito itemCarrito : productos) {
            Producto producto = productoRepository.findById(itemCarrito.getProducto().getIdProducto())
            .orElseThrow(()-> new EntityNotFoundException(Constantes.PRODUCTO_NO_EXISTENTE));
            if (producto.getTienda().getUsuario().equals(usuario)) {
                throw new OwnStoreException(Constantes.COMPRA_DESDE_MISMA_TIENDA);
            }
            producto.setStock(producto.getStock()- itemCarrito.getStock());
            total += itemCarrito.getProducto().getPrecio()*itemCarrito.getStock();
        }

        newPedido.setIva(total*IVA);
        newPedido.setTotal(total+newPedido.getIva());
        Integer idPago = crearPago(new PagoDto(pedidoDto.getPlataforma(), newPedido.getFecha(),newPedido.getTotal(), pedidoDto.getNumCuenta()));
        Pago pago = pagoRepository.getReferenceById(idPago);
        newPedido.setPago(pago);
        Integer idEnvio = crearEnvio(new EnvioDto(pedidoDto.getCalle(), pedidoDto.getColonia(), pedidoDto.getEstado(), pedidoDto.getCiudad(), pedidoDto.getNumCasa()));
        Envio envio = envioRepository.getReferenceById(idEnvio);
        newPedido.setEnvio(envio);
        pedidoRepository.save(newPedido);
        
        for (DetalleCarrito itemCarrito : productos) {
            PedidosProductos newPedidosProductos = new PedidosProductos();
            newPedidosProductos.setPedido(newPedido);
            newPedidosProductos.setProducto(itemCarrito.getProducto());
            newPedidosProductos.setPrecioVenta(itemCarrito.getProducto().getPrecio());
            pedidosProductosRepository.save(newPedidosProductos);
            itemCarrito.setActive(false);
            detalleCarritoRepository.save(itemCarrito);
        }

        
        pedidoDto.setTotal(newPedido.getTotal());
        pedidoDto.setIva(newPedido.getIva());
        pedidoDto.setFecha(newPedido.getFecha());
        pedidoDto.setIdPedidos(newPedido.getIdPedidos());

        respuesta.setExito(true);
        respuesta.getDatos().add(pedidoDto);
        respuesta.setMensaje(Constantes.EXITO_NUEVO_PEDIDO);
        return respuesta;
    }



    public RespuestaGenerica obtenerPedidosPorUsuario(Integer idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(()-> new EntityNotFoundException("El usuario no existe."));
        List<Pedido> pedidos = pedidoRepository.findByUsuario(usuario);
        if (pedidos == null || pedidos.isEmpty()) {
            throw new EntityNotFoundException("El usuario no tiene un historial de pedidos.");
        }

        RespuestaGenerica respuesta = new RespuestaGenerica();
        List<PedidoDto> pedidoDtos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            PedidoDto pedidoDto = new PedidoDto();
            pedidoDto.setUsuario(pedido.getUsuario().getIdUsuario());
            pedidoDto.setIdPedidos(pedido.getIdPedidos());
            pedidoDto.setNumCuenta(pedido.getPago().getNumCuenta());
            pedidoDto.setPlataforma(pedido.getPago().getPlataforma());
            pedidoDto.setCalle(pedido.getEnvio().getCalle());
            pedidoDto.setCiudad(pedido.getEnvio().getCiudad());
            pedidoDto.setColonia(pedido.getEnvio().getColonia());
            pedidoDto.setEstado(pedido.getEnvio().getEstado());
            pedidoDto.setNumCasa(pedido.getEnvio().getNumCasa());
            pedidoDto.setFecha(pedido.getFecha());
            pedidoDto.setIva(pedido.getIva());
            pedidoDto.setTotal(pedido.getTotal());
            pedidoDtos.add(pedidoDto);
        }

        respuesta.setExito(true);
        respuesta.getDatos().add(pedidoDtos);
        respuesta.setMensaje(Constantes.EXITO_PEDIDOS_CONSULTADOS);
        return respuesta;
    }
}
