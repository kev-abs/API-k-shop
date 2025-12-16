package com.example.demo.java1.Ventas.Checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
@CrossOrigin
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/cliente/{idCliente}")
    public List<Checkout> historialPorCliente(@PathVariable int idCliente) {
        return checkoutService.obtenerPorCliente(idCliente);
    }

    @PostMapping
    public void guardarCheckout(@RequestBody Checkout checkout) {
        checkoutService.registrarCheckout(checkout);
    }
}
