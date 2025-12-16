package com.example.demo.java1.Usuario.Fidelizacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Tag(name = "Puntos", description = "Operaciones sobre la tabla fidelizacion")
@RestController
@RequestMapping("/puntos")
public class ControllerPuntosCliente {

    @Autowired
    private ServicePuntosCliente puntosService;

    @GetMapping("")
    @Operation(summary = " Obtener puntos",
            description = "Nos permite obtener informacion acerca de los puntos que ha acumulado el cliente")
    public List<String> listarPuntosClientes() {
        return puntosService.obtenerPuntosClientes();
    }

    @PostMapping("")
    @Operation(summary = " Insertar puntos",
            description = "Nos permite insertar nuevos puntos de fidelizacion")
    public String crearPuntosCliente(@RequestBody PuntosClienteDTO puntosCliente) {
        int filas = puntosService.insertarPuntosCliente(
                puntosCliente.getIdCliente(),
                puntosCliente.getPuntos()
        );
        return (filas > 0) ? "Registro de puntos agregado correctamente" : "Error al agregar registro";
    }

    @PutMapping("/{id}")
    @Operation(summary = " Actualizar puntos",
            description = "Nos permite actualizar los puntos de fidelizacion")
    public String actualizarPuntosCliente(@PathVariable int id, @RequestBody PuntosClienteDTO puntosCliente) {
        int filas = puntosService.actualizarPuntosCliente(
                id,
                puntosCliente.getIdCliente(),
                puntosCliente.getPuntos()
        );
        return (filas > 0) ? "Registro de puntos actualizado correctamente" : "Registro no encontrado o sin cambios";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = " Eliminar puntos",
            description = "Nos permite eliminar los puntos de fidelizacion")
    public String eliminarPuntosCliente(@PathVariable int id) {
        int filas = puntosService.eliminarPuntosCliente(id);
        return (filas > 0) ? "Registro eliminado correctamente" : "Registro no encontrado";
    }
}
