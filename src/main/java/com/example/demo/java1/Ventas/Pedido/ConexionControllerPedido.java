package com.example.demo.java1.Ventas.Pedido;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class ConexionControllerPedido {

    private final ConexionServicePedido conexionServicePedido;

    public ConexionControllerPedido(ConexionServicePedido conexionServicePedido) {
        this.conexionServicePedido = conexionServicePedido;
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return conexionServicePedido.obtenerPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable int id) {
        try {
            Pedido pedido = conexionServicePedido.obtenerPedidoPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> agregar(@RequestBody Pedido pedido) {
        int filas = conexionServicePedido.agregarPedido(pedido);

        if (filas > 0){
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody Pedido pedido) {
        int filas = conexionServicePedido.actualizarPedido(id, pedido);
        return (filas > 0)
                ? ResponseEntity.ok("Pedido actualizado correctamente")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        int filas = conexionServicePedido.eliminarPedido(id);
        return (filas > 0)
                ? ResponseEntity.ok("Pedido eliminado correctamente")
                : ResponseEntity.notFound().build();
    }
}