package com.example.demo.java1.Ventas.Checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "Checkout", description = "Mostrar y guardar informacion sobre las ventas")
@RequestMapping("/checkout")
@CrossOrigin
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/cliente/{idCliente}")
    @Operation(
            summary = "Obtener historial de compras por cliente",
            description = "Permite consultar el historial de compras realizadas por un cliente específico mediante su identificador"
    )
    public List<Checkout> historialPorCliente(@PathVariable int idCliente) {
        return checkoutService.obtenerPorCliente(idCliente);
    }

    @PostMapping
    @Operation(
            summary = "Registrar compra (checkout)",
            description = "Permite registrar una compra realizada por un cliente, incluyendo dirección, método de pago y tipo de entrega"
    )
    public void guardarCheckout(@RequestBody Checkout checkout) {
        checkoutService.registrarCheckout(checkout);
    }
}
