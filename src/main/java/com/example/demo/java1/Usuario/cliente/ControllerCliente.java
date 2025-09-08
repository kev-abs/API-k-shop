package com.example.demo.java1.Usuario.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerCliente {

    @Autowired
    private ServiceCliente conexionService;

    // Listar clientes
    @GetMapping("/clientes")
    public List<String> listarClientes() {
        return conexionService.obtenerClientes();
    }

    // Crear cliente
    @PostMapping("/clientes")
    public String crearCliente(@RequestBody ClienteDTO cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            return "Error: El nombre no puede estar vacío";
        }

        int filas = conexionService.insertarCliente(
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getContrasena(),
                cliente.getFechaRegistro(),
                cliente.getEstado(),
                cliente.getDocumento(),
                cliente.getTelefono()
        );

        return (filas > 0) ? "Cliente agregado correctamente" : "Error al agregar cliente";
    }

    // Actualizar cliente
    @PutMapping("/clientes/{id}")
    public String actualizarCliente(@PathVariable int id, @RequestBody ClienteDTO cliente) {
        int filas = conexionService.actualizarCliente(
                id,
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getContrasena(),
                cliente.getFechaRegistro(),
                cliente.getEstado(),
                cliente.getDocumento(),
                cliente.getTelefono()
        );

        return (filas > 0) ? "Cliente actualizado correctamente" : "Cliente no encontrado o sin cambios";
    }

    // Eliminar cliente
    @DeleteMapping("/clientes/{id}")
    public String eliminarCliente(@PathVariable int id) {
        int filas = conexionService.eliminarCliente(id);
        return (filas > 0)
                ? "Cliente eliminado correctamente"
                : "Cliente no encontrado";
    }
}
