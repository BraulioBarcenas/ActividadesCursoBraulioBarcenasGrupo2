package com.braulio.tienda.service;

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
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.EnvioDto;
import com.braulio.tienda.data.dto.PagoDto;
import com.braulio.tienda.data.dto.PedidoDto;
import com.braulio.tienda.repository.CarritoRepository;
import com.braulio.tienda.repository.DetalleCarritoRepository;
import com.braulio.tienda.repository.EnvioRepository;
import com.braulio.tienda.repository.PagoRepository;
import com.braulio.tienda.repository.PedidoRepository;
import com.braulio.tienda.repository.PedidosProductosRepository;
import com.braulio.tienda.repository.UsuarioRepository;

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
    private CarritoRepository carritoRepository;
    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;
    @Autowired
    private PedidosProductosRepository pedidosProductosRepository;

    public Pago crearPago(PagoDto pagoDto){
        Pago pago = new Pago();

        pago.setCargo(pagoDto.getCargo());
        pago.setFecha(pagoDto.getFecha());
        pago.setNumCuenta(pagoDto.getNumCuenta());
        pago.setPlataforma(pagoDto.getPlataforma());
        pagoRepository.save(pago);
        
        return pago;
    }

    public Envio crearEnvio(EnvioDto envioDto){
        Envio envio = new Envio();
        envio.setCalle(envioDto.getCalle());
        envio.setCiudad(envioDto.getCiudad());
        envio.setColonia(envioDto.getColonia());
        envio.setEstado(envioDto.getEstado());
        envio.setNumCasa(envioDto.getNumCasa());
        envioRepository.save(envio);
        return envio;
    }


    public PedidoDto nuevoPedido(PedidoDto pedidoDto){
        double IVA = 0.16;
        Usuario usuario = usuarioRepository.getReferenceById(pedidoDto.getUsuario());

        if (usuario instanceof Usuario) {
            Pedido newPedido = new Pedido();
            newPedido.setFecha(new Date());
            newPedido.setUsuario(usuario);
            Integer total = 0;
            Carrito carrito = carritoRepository.findByUsuario(usuario).get(0);
            List<DetalleCarrito> productos = detalleCarritoRepository.findByCarritoAndActive(carrito,true);
            for (DetalleCarrito itemCarrito : productos) {
                total += itemCarrito.getProducto().getPrecio();
            }
            newPedido.setIva(total*IVA);
            newPedido.setTotal(total+newPedido.getIva());
            newPedido.setPago(crearPago(new PagoDto(pedidoDto.getPlataforma(), newPedido.getFecha(),newPedido.getTotal(), pedidoDto.getNumCuenta())));
            newPedido.setEnvio(crearEnvio(new EnvioDto(pedidoDto.getCalle(), pedidoDto.getColonia(), pedidoDto.getEstado(), pedidoDto.getCiudad(), pedidoDto.getNumCasa())));
            pedidoRepository.save(newPedido);
            
            for (DetalleCarrito itemCarrito : productos) {
                PedidosProductos newPedidosProductos = new PedidosProductos();
                newPedidosProductos.setPedido(newPedido);
                newPedidosProductos.setProducto(itemCarrito.getProducto());
                newPedidosProductos.setPrecioVenta(itemCarrito.getProducto().getPrecio());
                pedidosProductosRepository.save(newPedidosProductos);
                
            }

        }
    

        return pedidoDto;
    }
}
