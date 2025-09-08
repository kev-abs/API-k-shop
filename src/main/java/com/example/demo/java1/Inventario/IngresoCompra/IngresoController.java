package com.example.demo.java1.Inventario.IngresoCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngresoController {
    @Autowired
    public IngresoService ingresoService;

    @GetMapping("/ingresocompra")
    public List<String> obtenerIngreso() {
        return ingresoService.obtenerIngreso();
    }

    @PostMapping("/ingresocompra")
    public String agregarIngreso(@RequestBody IngresoCompra ingresoCompra) {
        boolean insertado = ingresoService.agregarIngreso(ingresoCompra);
        if (insertado) {
            return "Ingreso agregado correctamente";
        } else {
            return "Error: No se pudo agregar el ingreso. Verifica los IDs de empleado y proveedor.";
        }
    }

    @PutMapping("/ingresocompra/{id}")
    public ResponseEntity<String> actualizarIngreso(@PathVariable int id,
                                                    @RequestBody IngresoCompra ingresoCompra) {
        boolean ok = ingresoService.actualizarIngreso(id, ingresoCompra);
        return ok
                ? ResponseEntity.ok("Ingreso actualizado correctamente")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/ingresocompra/{id}")
    public ResponseEntity<String> eliminarIngreso(@PathVariable int id) {
        boolean ok = ingresoService.eliminarIngreso(id);
        return ok ? ResponseEntity.ok("Ingreso eliminado correctamente")
                : ResponseEntity.notFound().build();
    }
}





