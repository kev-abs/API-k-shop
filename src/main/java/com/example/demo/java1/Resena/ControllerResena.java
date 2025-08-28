package com.example.demo.java1.Resena;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resena")
public class ControllerResena {
    private ConexionResena conexionService;

    public ControllerResena(ConexionResena conexionService) {
        this.conexionService = conexionService;
    }

    @GetMapping
    public List<Resena> listarResenas() {
        return conexionService.obtenerResenas();
    }

    @PostMapping
    public String agregarResena(@RequestBody Resena resena) {
        int filas = conexionService.insertarResena(
                resena.getIdCliente(),
                resena.getIdProducto(),
                resena.getCalificacion(),
                resena.getComentario(),
                resena.getFecha()
        );
        return (filas > 0) ? "Reseña creada correctamente" : "No se pudo crear la reseña";
    }

    @PutMapping("/{id}")
    public String actualizarResena(@PathVariable int id, @RequestBody Resena reseña) {
        int filas = conexionService.actualizarResena(
                id,
                reseña.getIdCliente(),
                reseña.getIdProducto(),
                reseña.getCalificacion(),
                reseña.getComentario(),
                reseña.getFecha()
        );
        return (filas > 0) ? "Reseña actualizada correctamente" : "No se pudo actualizar la reseña";
    }

    @DeleteMapping("/{id}")
    public String eliminarResena(@PathVariable int id) {
        int filas = conexionService.eliminarResena(id);
        return (filas > 0) ? "Reseña eliminada correctamente" : "No se pudo eliminar la reseña";
    }
}
