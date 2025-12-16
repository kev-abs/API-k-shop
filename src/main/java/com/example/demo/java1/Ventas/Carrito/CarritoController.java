package com.example.demo.java1.Ventas.Carrito;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Carrito", description = "Informacion temporal sobre la compra")
@RequestMapping("/carrito")
@CrossOrigin
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    @Operation(
            summary = "Agregar producto al carrito",
            description = "Permite agregar un producto al carrito de compras de un cliente de forma temporal"
    )
    public void agregar(@RequestBody Carrito carrito) {
        carritoService.agregarAlCarrito(carrito);
    }

    @GetMapping("/{idCliente}")
    @Operation(
            summary = "Obtener carrito del cliente",
            description = "Permite consultar los productos agregados al carrito de un cliente mediante su identificador"
    )
    public List<Carrito> listar(@PathVariable int idCliente) {
        return carritoService.obtenerCarrito(idCliente);
    }

    @DeleteMapping("/{idCliente}")
    @Operation(
            summary = "Vaciar carrito del cliente",
            description = "Permite eliminar todos los productos del carrito de compras de un cliente"
    )
    public void vaciar(@PathVariable int idCliente) {
        carritoService.vaciarCarrito(idCliente);
    }

}

