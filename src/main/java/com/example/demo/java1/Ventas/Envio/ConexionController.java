package com.example.demo.java1.Ventas.Envio;

import ConexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envio")
public class ConexionController {

    @Autowired
    private ConexionService conexionService;

    // GET /envio -> obtener todos los envíos
    @GetMapping
    public List<Envio> obtenerEnvios() {
        return conexionService.obtenerEnvios();
    }

    // POST /envio -> agregar un envío
    @PostMapping
    public String agregarEnvio(@RequestBody Envio envio) {
        conexionService.agregarEnvio(envio);
        return "Envío agregado correctamente";
    }

    // PUT /envio/{id} -> actualizar un envío
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEnvio(@PathVariable int id, @RequestBody Envio envio) {
        boolean actualizado = conexionService.actualizarEnvio(id, envio);
        if (actualizado) {
            return ResponseEntity.ok("Envío actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /envio/{id} -> eliminar un envío
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
