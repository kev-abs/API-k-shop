package com.example.demo.java1.Inventario.Cupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConexionControllerCupon {
    @Autowired
    private ConexionServiceCupon conexionServiceCupon;

    @GetMapping("/cupon")
    public List<String> obtenerCupon() {
        return conexionServiceCupon.obtenerCupon();
    }

    @PostMapping("/cupon")
    public String agregarCupon(@RequestBody Cupon cupon) {
        conexionServiceCupon.agregarCupon(cupon);
        return "Cupon agregado";
    }

    @PutMapping("/cupon/{id}")
    public ResponseEntity<String> actualizarCupon(@PathVariable int id, @RequestBody Cupon cupon) {
        boolean actualizado = conexionServiceCupon.actualizarCupon(id, cupon);
        if (actualizado) {
            return ResponseEntity.ok("Cupon actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cupon/{id}")
    public ResponseEntity<String> eliminarCupon(@PathVariable int id) {
        boolean eliminado = conexionServiceCupon.eliminarCupon(id);
        if (eliminado) {
            return ResponseEntity.ok("Cupon eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
