package com.example.demo.men1.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerProductos {

    @Autowired
    private ServiceProductos conexionService;

    // Listar productos
    @GetMapping("/productos")
    public List<String> listarProductos() {
        return conexionService.obtenerProductos();
    }

    // Crear producto
    @PostMapping("/productos")
    public String crearProducto(@RequestBody Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            return "Error: El nombre no puede estar vacío";
        }

        int filas = conexionService.insertarProducto(producto);
        return (filas > 0) ? "Producto insertado correctamente" : "Error al insertar producto";
    }

    // Actualizar producto
    @PutMapping("/productos/{id}")
    public String actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        int filas = conexionService.actualizarProducto(id, producto);
        return (filas > 0) ? "Producto actualizado correctamente" : "Producto no encontrado o sin cambios";
    }

    // Eliminar producto
    @DeleteMapping("/productos/{id}")
    public String eliminarProducto(@PathVariable int id) {
        int filas = conexionService.eliminarProducto(id);
        return (filas > 0) ? "Producto eliminado correctamente" : "Producto no encontrado";
    }
}
