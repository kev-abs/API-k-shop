package com.example.demo.java1.Usuario.ListaDeseos;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Lista de Deseos", description = "Operaciones sobre la tabla lista de Deseos")
@RestController
@RequestMapping("/lista_deseos") //
public class ControllerListaDeseos {
    private final ConexionListaDeseos conexionService;

    public ControllerListaDeseos(ConexionListaDeseos conexionService) {
        this.conexionService = conexionService;
    }


    @GetMapping
    @Operation(summary = " Obtener lista de deseos",
            description = "Nos permite obtener informacion acerca de lo que desea el cliente")
    public List<ListaDeseos> listarListasDeseos() {
        return conexionService.obtenerListasDeseos();
    }


    @PostMapping
    @Operation(summary = " Insertar lista de deseos",
            description = "Permite insertar nuevos productos a la lista de deseos")
    public String agregarListaDeseos(@RequestBody ListaDeseos listadeseos) {
        int filas = conexionService.insertarListaDeseos(
                listadeseos.getIdCliente(),
                listadeseos.getFechaCreacion() // 👈 camelCase
        );
        return (filas > 0) ? "Lista de deseos creada correctamente"
                : "No se pudo crear la lista de deseos";
    }


    @PutMapping("/{id}")
    @Operation(summary = " Actualizar lista de deseos",
            description = "Nos permite actualizar los productos de la lista de deseos")
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
    @Operation(summary = " Eliminar lista de deseos",
            description = "Nos permite eliminar los productos de nuestra lista de deseos")
    public String eliminarListaDeseos(@PathVariable int id) {
        int filas = conexionService.eliminarListaDeseos(id);
        return (filas > 0) ? "Lista de deseos eliminada correctamente"
                : "No se pudo eliminar la lista de deseos";
    }
}
