package com.example.demo.java1.Reseña;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reseña")
public class ControllerReseña {
    private final ConexionReseña conexionService;

    public ControllerReseña(ConexionReseña conexionService) {
        this.conexionService = conexionService;
    }

    @GetMapping
    public List<Reseña> listarReseñas() {
        return conexionService.obtenerReseñas();
    }

    @PostMapping
    public String agregarReseña(@RequestBody Reseña reseña) {
        int filas = conexionService.insertarReseña(
                reseña.getIdCliente(),
                reseña.getIdProducto(),
                reseña.getCalificacion(),
                reseña.getComentario(),
                reseña.getFecha()
        );
        return (filas > 0) ? "Reseña creada correctamente" : "No se pudo crear la reseña";
    }

    @PutMapping("/{id}")
    public String actualizarReseña(@PathVariable int id, @RequestBody Reseña reseña) {
        int filas = conexionService.actualizarReseña(
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
    public String eliminarReseña(@PathVariable int id) {
        int filas = conexionService.eliminarReseña(id);
        return (filas > 0) ? "Reseña eliminada correctamente" : "No se pudo eliminar la reseña";
    }
}
