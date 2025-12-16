package com.example.demo.java1.Ventas.Carrito;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@CrossOrigin
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public void agregar(@RequestBody Carrito carrito) {
        carritoService.agregarAlCarrito(carrito);
    }

    @GetMapping("/{idCliente}")
    public List<Carrito> listar(@PathVariable int idCliente) {
        return carritoService.obtenerCarrito(idCliente);
    }
    @DeleteMapping("/{idCliente}")
    public void vaciar(@PathVariable int idCliente) {
        carritoService.vaciarCarrito(idCliente);
    }

}

