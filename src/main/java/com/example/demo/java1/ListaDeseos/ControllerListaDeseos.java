package com.example.demo.java1.ListaDeseos;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lista_deseos") //
public class ControllerListaDeseos {
    private final ConexionListaDeseos conexionService;

    public ControllerListaDeseos(ConexionListaDeseos conexionService) {
        this.conexionService = conexionService;
    }


    @GetMapping
    public List<ListaDeseos> listarListasDeseos() {
        return conexionService.obtenerListasDeseos();
    }


    @PostMapping
    public String agregarListaDeseos(@RequestBody ListaDeseos listadeseos) {
        int filas = conexionService.insertarListaDeseos(
                listadeseos.getIdCliente(),
                listadeseos.getFechaCreacion() // 👈 camelCase
        );
        return (filas > 0) ? "Lista de deseos creada correctamente"
                : "No se pudo crear la lista de deseos";
    }


    @PutMapping("/{id}")
    public String actualizarListaDeseos(@PathVariable int id, @RequestBody ListaDeseos listadeseos) {
        int filas = conexionService.actualizarListaDeseos(
                id,
                listadeseos.getIdCliente(),
                listadeseos.getFechaCreacion() // 👈 camelCase
        );
        return (filas > 0) ? "Lista de deseos actualizada correctamente"
                : "No se pudo actualizar la lista de deseos";
    }

    // DELETE - Eliminar por ID
    @DeleteMapping("/{id}")
    public String eliminarListaDeseos(@PathVariable int id) {
        int filas = conexionService.eliminarListaDeseos(id);
        return (filas > 0) ? "Lista de deseos eliminada correctamente"
                : "No se pudo eliminar la lista de deseos";
    }
}
