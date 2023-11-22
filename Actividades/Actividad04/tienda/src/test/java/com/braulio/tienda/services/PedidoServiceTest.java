package com.braulio.tienda.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.DetalleCarrito;
import com.braulio.tienda.data.Envio;
import com.braulio.tienda.data.Pago;
import com.braulio.tienda.data.Pedido;
import com.braulio.tienda.data.Producto;
import com.braulio.tienda.data.Tienda;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.PedidoDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.repository.CarritoRepository;
import com.braulio.tienda.repository.DetalleCarritoRepository;
import com.braulio.tienda.repository.EnvioRepository;
import com.braulio.tienda.repository.PagoRepository;
import com.braulio.tienda.repository.PedidoRepository;
import com.braulio.tienda.repository.PedidosProductosRepository;
import com.braulio.tienda.repository.ProductoRepository;
import com.braulio.tienda.repository.UsuarioRepository;
import com.braulio.tienda.service.PedidoService;
import com.braulio.tienda.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
    
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private PagoRepository pagoRepository;
    @Mock
    private EnvioRepository envioRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private CarritoRepository carritoRepository;
    @Mock
    private DetalleCarritoRepository detalleCarritoRepository;
    @Mock
    private PedidosProductosRepository pedidosProductosRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Usuario usuario;
    private PedidoDto pedidoDto;
    private Pedido pedido;
    private Carrito carrito;
    private DetalleCarrito detalleCarrito;
    private Producto producto;
    private Tienda tienda;
    private Pago pago;
    private Envio envio;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        
        usuario.setIdUsuario(1);
        usuario.setNombre("Pedro");
        usuario.setApPat("Perez");
        usuario.setApMat("Hernandez");
        usuario.setEmail("pedro.email@gmail.com");


        pedidoDto = new PedidoDto();
        pedidoDto.setUsuario(usuario.getIdUsuario());
        pedidoDto.setPlataforma("paypal");
        pedidoDto.setNumCuenta("15618194");
        pedidoDto.setCalle("Calle Mango");
        pedidoDto.setColonia("Frutas");
        pedidoDto.setEstado("Guanajuato");
        pedidoDto.setCiudad("Acambaro");
        pedidoDto.setNumCasa(300);

        carrito = new Carrito();
        carrito.setIdCarrito(1);
        carrito.setUsuario(usuario);
        
        tienda = new Tienda();

        tienda.setIdTienda(1);
        tienda.setNombre("Juanchos");
        tienda.setDescripcion("Descripcion");
        tienda.setUsuario(usuario);

        producto = new Producto();

        producto.setIdProducto(1);
        producto.setNombre("Reloj Cuarzo");
        producto.setDescripcion("Reloj con movimiento de cuarzo");
        producto.setPrecio(200);
        producto.setStock(3);
        producto.setFechaCaducidad(null);
        producto.setMarca("Timex");
        producto.setCategoria("Accesorios");
        producto.setColor("Negro");
        producto.setTalla("40mm");
        producto.setImg("/ImagenRelog.png");
        producto.setTienda(tienda);
        

        detalleCarrito = new DetalleCarrito();
        detalleCarrito.setActive(true);
        detalleCarrito.setCarrito(carrito);
        detalleCarrito.setIdDetalleCarrito(1);
        detalleCarrito.setProducto(producto);
        detalleCarrito.setStock(3);

        pago = new Pago();
        pago.setIdPagos(1);
        pago.setCargo(15697);
        pago.setFecha(new Date());
        pago.setNumCuenta("1864418");
        pago.setPlataforma("Paypal");

        envio = new Envio();
        envio.setIdEnvio(1);
        envio.setCalle("Calle Mango");
        envio.setColonia("Frutas");
        envio.setEstado("Guanajuato");
        envio.setCiudad("Acambaro");
        envio.setNumCasa(300);

        pedido = new Pedido();
        pedido.setEnvio(envio);
        pedido.setFecha(new Date());
        pedido.setIdPedidos(1);
        pedido.setTotal(pago.getCargo());
        pedido.setIva(pago.getCargo()*0.16);
        pedido.setPago(pago);
        pedido.setUsuario(usuario);
    }

    @Test
    void crearPedidoShouldReturnPedidoDto(){
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(carritoRepository.findByUsuario(any(Usuario.class))).thenReturn(Arrays.asList(carrito));
        when(detalleCarritoRepository.findByCarritoAndActive(any(Carrito.class),anyBoolean())).thenReturn(Arrays.asList(detalleCarrito));
        when(productoRepository.findById(anyInt())).thenReturn(Optional.of(producto));
        when(pagoRepository.getReferenceById(anyInt())).thenReturn(pago);
        when(envioRepository.getReferenceById(anyInt())).thenReturn(envio);
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido pedido = invocation.getArgument(0);
            pedido.setIdPedidos(1);
            return pedido;
        });
        when(pagoRepository.save(any(Pago.class))).thenAnswer(invocation -> {
            Pago pago = invocation.getArgument(0);
            pago.setIdPagos(1);
            return pago;
        });
        when(envioRepository.save(any(Envio.class))).thenAnswer(invocation -> {
            Envio envio = invocation.getArgument(0);
            envio.setIdEnvio(1);
            return envio;
        });

        RespuestaGenerica respuesta = pedidoService.nuevoPedido(pedidoDto);

        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVO_PEDIDO, respuesta.getMensaje());
        assertNotNull(pedidoDto.getUsuario());
        
        verify(usuarioRepository).findById(anyInt());
        verify(carritoRepository).findByUsuario(any(Usuario.class));
        verify(detalleCarritoRepository).findByCarritoAndActive(any(Carrito.class), anyBoolean());
        verify(productoRepository).findById(anyInt());
        verify(pagoRepository).getReferenceById(anyInt());
        verify(envioRepository).getReferenceById(anyInt());
        verify(pedidoRepository).save(any(Pedido.class));
        verify(pagoRepository).save(any(Pago.class));
        verify(envioRepository).save(any(Envio.class));
    }
    
    @Test
    void crearPedidoWithoutUser(){
        when(usuarioRepository.findById(anyInt())).thenThrow(new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));
        
        assertThrows(EntityNotFoundException.class, ()-> pedidoService.nuevoPedido(pedidoDto));

    }
    
    
    @Test
    void obtenerPedidosPorUsuarioShouldReturnListOfPedidoDto(){
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(pedidoRepository.findByUsuario(any(Usuario.class))).thenReturn(Arrays.asList(pedido));
        
        RespuestaGenerica respuesta = pedidoService.obtenerPedidosPorUsuario(usuario.getIdUsuario());
        
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_PEDIDOS_CONSULTADOS, respuesta.getMensaje());
        
        verify(usuarioRepository).findById(anyInt());
        verify(pedidoRepository).findByUsuario(any(Usuario.class));
    }
    
    @Test
    void obtenerPedidosPorUsuarioWithoutUser(){
        when(usuarioRepository.findById(anyInt())).thenThrow(new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));
        
        assertThrows(EntityNotFoundException.class, ()-> pedidoService.obtenerPedidosPorUsuario(usuario.getIdUsuario()));
    
    }
}

