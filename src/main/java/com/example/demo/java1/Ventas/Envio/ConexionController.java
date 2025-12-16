package com.example.demo.java1.Ventas.Envio;

import com.example.demo.java1.Ventas.Pedido.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Envio", description = "Operaciones sobre la tabla envio")
@RequestMapping("/envio")
public class ConexionController {

    @Autowired
    private ConexionService conexionService;


    @GetMapping
    @Operation(
            summary = "Obtener envíos",
            description = "Permite obtener el listado de todos los envíos registrados"
    )
    public List<Envio> obtenerEnvios() {
        return conexionService.obtenerEnvios();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener envío por ID",
            description = "Permite consultar la información de un envío específico mediante su identificador"
    )

    public ResponseEntity<Envio> obtenerPorId(@PathVariable int id) {
        try {
            Envio envio = conexionService.obtenerEnvioPorId(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    @Operation(
            summary = "Agregar envío",
            description = "Permite registrar un nuevo envío en el sistema"
    )
    public Envio agregarEnvio(@RequestBody Envio envio) {
        conexionService.agregarEnvio(envio);
        return envio;
    }

    @Operation(
            summary = "Actualizar envío",
            description = "Permite actualizar la información de un envío existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEnvio(@PathVariable int id, @RequestBody Envio envio) {
        boolean actualizado = conexionService.actualizarEnvio(id, envio);
        if (actualizado) {
            return ResponseEntity.ok("Envío actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Eliminar envío",
            description = "Permite eliminar un envío del sistema mediante su identificador"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEnvio(@PathVariable int id) {
        boolean eliminado = conexionService.eliminarEnvio(id);
        if (eliminado) {
            return ResponseEntity.ok("Envío eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
