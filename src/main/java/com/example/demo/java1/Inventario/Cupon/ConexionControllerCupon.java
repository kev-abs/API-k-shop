package com.example.demo.java1.Inventario.Cupon;

import io.swagger.v3.oas.annotations.Operation;          // 🔹 Swagger
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cupon")
@Tag(
        name = "Cupones",
        description = "Operaciones relacionadas con la gestión de cupones")

public class ConexionControllerCupon {
    @Autowired
    private ConexionServiceCupon conexionServiceCupon;

    @GetMapping
    @Operation(summary = "Obtener cupones",
            description = "Devuelve la lista de todos los cupones registrados")
    public List<Cupon> obtenerCupon() {
        return conexionServiceCupon.obtenerCupon();
    }

    @PostMapping
    @Operation(
            summary = "Agregar cupón",
            description = "Registra un nuevo cupón en el sistema"
    )
    public String agregarCupon(@RequestBody Cupon cupon) {
        conexionServiceCupon.agregarCupon(cupon);
        return "Cupon agregado";
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar cupón",
            description = "Actualiza la información de un cupón existente"
    )
    @ApiResponses({ // 🔹 Swagger: respuestas posibles
            @ApiResponse(responseCode = "200", description = "Cupón actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cupón no encontrado")
    })
    public ResponseEntity<String> actualizarCupon(@PathVariable int id, @RequestBody Cupon cupon) {
        boolean actualizado = conexionServiceCupon.actualizarCupon(id, cupon);
        if (actualizado) {
            return ResponseEntity.ok("Cupon actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar cupón",
            description = "Elimina un cupón según su identificador"
    )
    public Map<String, String> eliminarCupon(@PathVariable int id) {
        boolean eliminado = conexionServiceCupon.eliminarCupon(id);
        if (eliminado) {
            return Map.of("message","Cupon eliminado correctamente");
        } else {
            return Map.of("message","Cupon no eliminado");
        }
    }

}
