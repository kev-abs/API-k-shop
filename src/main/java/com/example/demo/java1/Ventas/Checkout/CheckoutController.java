package com.example.demo.java1.Ventas.Checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@CrossOrigin
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping
    public void guardarCheckout(@RequestBody Checkout checkout) {
        checkoutService.registrarCheckout(checkout);
    }
}
