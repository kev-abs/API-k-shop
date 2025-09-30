package com.example.demo.java1.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ConexionControllerPedido {

    @Autowired
    private ConexionServicePedido conexionService;

    // Listar pedidos
    @GetMapping("/pedidos")
    public List<Pedido> listarPedidos() {
        return conexionService.obtenerPedidos();
    }

    // Crear pedido
    @PostMapping("/pedidos")
    public String crearPedido(@RequestBody Pedido pedido) {
        int filas = conexionService.agregarPedido(pedido);
        return (filas > 0) ? "Pedido creado correctamente" : "Error al crear el pedido";
    }

    // Actualizar pedido
    @PutMapping("/pedidos/{id}")
    public String actualizarPedido(@PathVariable int id, @RequestBody Pedido pedido) {
        int filas = conexionService.actualizarPedido(id, pedido);
        return (filas > 0) ? "Pedido actualizado correctamente" : "Pedido no encontrado";
    }

    // Eliminar pedido
    @DeleteMapping("/pedidos/{id}")
    public String eliminarPedido(@PathVariable int id) {
        int filas = conexionService.eliminarPedido(id);
        return (filas > 0) ? "Pedido eliminado correctamente" : "Pedido no encontrado";
    }
}
