package com.example.demo.java1.Ventas.Pedido;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pedido", description = "Operaciones sobre la tabla pedido")
@RequestMapping("/pedido")
public class ConexionControllerPedido {

    private final ConexionServicePedido conexionServicePedido;

    public ConexionControllerPedido(ConexionServicePedido conexionServicePedido) {
        this.conexionServicePedido = conexionServicePedido;
    }

    @GetMapping
    @Operation(summary = " Obtener Pedido",
            description = "Nos permite obtener datos de los pedidos ya registrados")
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
    @Operation(summary = "Agregar Pedidos",
            description = "Nos permite agregar pedidos")
    public ResponseEntity<Pedido> agregar(@RequestBody Pedido pedido) {
        int filas = conexionServicePedido.agregarPedido(pedido);

        if (filas > 0){
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Pedidos",
            description = "Nos permite actulizar los pedidos")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody Pedido pedido) {
        int filas = conexionServicePedido.actualizarPedido(id, pedido);
        return (filas > 0)
                ? ResponseEntity.ok("Pedido actualizado correctamente")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Pedidos",
            description = "Nos permite eliminar los pedidos")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        int filas = conexionServicePedido.eliminarPedido(id);
        return (filas > 0)
                ? ResponseEntity.ok("Pedido eliminado correctamente")
                : ResponseEntity.notFound().build();
    }
}