package com.example.demo.java1.Ventas.Pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pago")
@CrossOrigin
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // Cliente consulta pago
    @GetMapping("/pedido/{idPedido}")
    public Pago obtener(@PathVariable int idPedido) {
        return pagoService.obtenerPorPedido(idPedido);
    }

    // Admin cambia estado
    @PutMapping("/{idPago}/estado/{estado}")
    public void cambiarEstado(
            @PathVariable int idPago,
            @PathVariable String estado) {

        pagoService.cambiarEstado(idPago, estado);
    }
}
