package com.example.demo.java1.Ventas.Pedido;

import com.example.demo.java1.Ventas.DetallePedido.DetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Historial del cliente
    @GetMapping("/cliente/{idCliente}")
    public List<Pedido> listarPorCliente(@PathVariable int idCliente) {
        return pedidoService.listarPorCliente(idCliente);
    }

    // Ver pedido específico
    @GetMapping("/{idPedido}")
    public Pedido obtener(@PathVariable int idPedido) {
        return pedidoService.obtenerPorId(idPedido);
    }

    // Cambiar estado
    @PutMapping("/{idPedido}/estado/{estado}")
    public void cambiarEstado(
            @PathVariable int idPedido,
            @PathVariable String estado) {

        pedidoService.cambiarEstado(idPedido, estado);
    }
    // Obtener detalle del pedido (productos)
    @GetMapping("/{idPedido}/detalle")
    public List<DetallePedido> detalle(@PathVariable int idPedido) {
        return pedidoService.obtenerDetallePedido(idPedido);
    }

}
