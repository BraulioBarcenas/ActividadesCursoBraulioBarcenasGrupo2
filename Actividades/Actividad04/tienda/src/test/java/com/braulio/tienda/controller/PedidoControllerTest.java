package com.braulio.tienda.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.braulio.tienda.data.Envio;
import com.braulio.tienda.data.Pago;
import com.braulio.tienda.data.Pedido;
import com.braulio.tienda.data.Usuario;
import com.braulio.tienda.data.dto.PedidoDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.service.PedidoService;
import com.braulio.tienda.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {
    
    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    private Pedido pedido;
    private PedidoDto pedidoDto;
    private Usuario usuario;
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
    void nuevoPedidoOK(){
        when(pedidoService.nuevoPedido(any(PedidoDto.class))).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(pedido));
            respuesta.setMensaje(Constantes.EXITO_NUEVO_PEDIDO);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = pedidoController.nuevoPedido(pedidoDto).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_NUEVO_PEDIDO, controllerResponse.getMensaje());

        verify(pedidoService).nuevoPedido(any(PedidoDto.class)); 
    }

    @Test
    void getPedidosOk(){
        when(pedidoService.obtenerPedidosPorUsuario(anyInt())).thenAnswer(invocation->{
            RespuestaGenerica respuesta = new RespuestaGenerica();
            respuesta.setExito(true);
            respuesta.getDatos().add(Arrays.asList(pedido));
            respuesta.setMensaje(Constantes.EXITO_PEDIDOS_CONSULTADOS);
            return respuesta;
        });

        RespuestaGenerica controllerResponse = pedidoController.getPedidos(usuario.getIdUsuario()).getBody();
        
        assertNotNull(controllerResponse);
        assertTrue(controllerResponse.isExito());
        assertEquals(200, controllerResponse.getCodigo());
        assertFalse(controllerResponse.getDatos().isEmpty());
        assertEquals(Constantes.EXITO_PEDIDOS_CONSULTADOS, controllerResponse.getMensaje());

        verify(pedidoService).obtenerPedidosPorUsuario(anyInt());
    }
}
