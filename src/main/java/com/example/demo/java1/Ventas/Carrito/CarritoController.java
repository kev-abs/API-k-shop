package com.example.demo.java1.Ventas.Carrito;

import com.example.demo.java1.Ventas.Carrito.dto.*;
import com.example.demo.java1.Ventas.DetalleCarrito.DetalleCarrito;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@CrossOrigin
@Tag(name = "Carrito", description = "Información temporal sobre la compra")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Agregar producto
    @PostMapping
    public void agregar(@RequestBody AgregarProductoRequest req) {

        carritoService.agregarProducto(
                req.getIdCliente(),
                req.getIdProducto(),
                req.getCantidad()
        );
    }

    // Vaciar carrito
    @DeleteMapping("/{idCliente}")
    public void vaciar(@PathVariable int idCliente) {
        carritoService.vaciarCarrito(idCliente);
    }

    @PutMapping("/cantidad")
    public void actualizarCantidad(@RequestBody ActualizarCantidadRequest req) {
        carritoService.actualizarCantidad(
                req.getIdCliente(),
                req.getIdProducto(),
                req.getCantidad()
        );
    }

    @DeleteMapping("/producto")
    public ResponseEntity<?> eliminarProducto(@RequestBody EliminarProductoRequest request) {
        carritoService.eliminarProducto(
                request.getIdCliente(),
                request.getIdProducto()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idCliente}")
    public CarritoResponse listar(@PathVariable int idCliente) {
        return carritoService.obtenerCarritoCompleto(idCliente);
    }
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request) {

        carritoService.checkout(
                request.getIdCliente(),
                request.getDireccion(),
                request.getCiudad(),
                request.getMetodoPago()
        );

        return ResponseEntity.ok().build();
    }



}

