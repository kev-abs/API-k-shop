package com.example.demo.java1.Usuario.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ControllerCliente {

    @Autowired
    private ServiceCliente conexionService;

    // Listar clientes
    @GetMapping("/clientes")
    public List<ClienteDTO> obtenerClientes() {
        return conexionService.obtenerClientes(); // <-- antes ponías ServiceCliente, debe ser el objeto
    }

    // Crear cliente
    @PostMapping("/clientes")
    public ClienteDTO crearCliente(@RequestBody ClienteDTO cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre no puede estar vacío");
        }

        int filas = conexionService.insertarCliente(
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getContrasena(),
                cliente.getDocumento(),
                cliente.getTelefono()
        );

        if (filas > 0) {
            return cliente; // Devuelve el cliente recién creado en JSON
        } else {
            throw new RuntimeException("Error al agregar cliente");
        }
    }

    // Actualizar cliente
    @PutMapping("/clientes/{id}")
    public ClienteDTO actualizarCliente(@PathVariable int id, @RequestBody ClienteDTO cliente) {
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

        if (filas > 0) {
            cliente.setIdCliente(id); // Asegura que el ID esté correcto
            return cliente; // Devuelve el cliente actualizado en JSON
        } else {
            throw new RuntimeException("Cliente no encontrado o sin cambios");
        }
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
