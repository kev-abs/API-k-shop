package com.example.demo.java1.Usuario.Fidelizacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntos")
public class ControllerPuntosCliente {

    @Autowired
    private ServicePuntosCliente puntosService;

    @GetMapping("")
    public List<String> listarPuntosClientes() {
        return puntosService.obtenerPuntosClientes();
    }

    @PostMapping("")
    public String crearPuntosCliente(@RequestBody PuntosClienteDTO puntosCliente) {
        int filas = puntosService.insertarPuntosCliente(
                puntosCliente.getIdCliente(),
                puntosCliente.getPuntos()
        );
        return (filas > 0) ? "Registro de puntos agregado correctamente" : "Error al agregar registro";
    }

    @PutMapping("/{id}")
    public String actualizarPuntosCliente(@PathVariable int id, @RequestBody PuntosClienteDTO puntosCliente) {
        int filas = puntosService.actualizarPuntosCliente(
                id,
                puntosCliente.getIdCliente(),
                puntosCliente.getPuntos()
        );
        return (filas > 0) ? "Registro de puntos actualizado correctamente" : "Registro no encontrado o sin cambios";
    }

    @DeleteMapping("/{id}")
    public String eliminarPuntosCliente(@PathVariable int id) {
        int filas = puntosService.eliminarPuntosCliente(id);
        return (filas > 0) ? "Registro eliminado correctamente" : "Registro no encontrado";
    }
}
