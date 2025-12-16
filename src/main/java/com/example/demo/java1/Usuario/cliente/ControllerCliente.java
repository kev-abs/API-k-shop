package com.example.demo.java1.Usuario.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

@Tag(name = "Clientes", description = "Operaciones sobre la tabla clientes")
@CrossOrigin(origins = "*")
@RestController
public class ControllerCliente {

    @Autowired
    private ServiceCliente conexionService;

    // Listar clientes
    @GetMapping("/clientes")
    @Operation(summary = " Obtener clientes",
            description = "Nos permite ver los clientes que están registrados")
    public List<ClienteDTO> obtenerClientes() {
        return conexionService.obtenerClientes(); // <-- antes ponías ServiceCliente, debe ser el objeto
    }

    // Crear cliente
    @PostMapping("/clientes")
    @Operation(summary = " Insertar clientes",
            description = "Nos permite registrar nuevos clientes")
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
    @Operation(summary = " Actualizar clientes",
            description = "Nos permite actualizar los datos de los clientes ya registrados")
    public ClienteDTO actualizarCliente(@PathVariable int id, @RequestBody ClienteDTO cliente) {
        int filas = conexionService.actualizarCliente(
                id,
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getContrasena(),
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
    @Operation(summary = " Eliminar clientes",
            description = "Nos permite eliminar los clientes")
    public Map<String, String> eliminarCliente(@PathVariable int id) {
        int filas = conexionService.eliminarCliente(id);
        if (filas > 0) {
            return Map.of("message", "Cliente eliminado correctamente");
        } else {
            return Map.of("message", "Cliente no encontrado");
        }
    }
}
