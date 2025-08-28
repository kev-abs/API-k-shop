package com.example.demo.java1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConexionController {
    @Autowired
    private ConexionService conexionService;


    @GetMapping("/cupon")
    public List<String> obtenerCupon() {
        return conexionService.obtenerCupon();
    }
    @GetMapping("/proveedor")
    public List<String> obtenerProveedor() {
        return conexionService.obtenerProveedor();
    }

    @PostMapping("/cupon")
    public String agregarCupon(@RequestBody Cupon cupon) {
        conexionService.agregarCupon(cupon);
        return "Cupon agregado";
    }

    @PostMapping("/proveedor")
    public String agregarProveedor(@RequestBody Proveedor proveedor) {
        conexionService.agregarProveedor(proveedor);
        return "Proveedor agregado";
    }



    @PutMapping("/cupon/{id}")
    public ResponseEntity<String> actualizarCupon(@PathVariable int id, @RequestBody Cupon cupon) {
        boolean actualizado = conexionService.actualizarCupon(id, cupon);
        if (actualizado) {
            return ResponseEntity.ok("Cupon actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/proveedor/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        boolean actualizado = conexionService.actualizarProveedor(id,proveedor);
        if (actualizado) {
            return ResponseEntity.ok("Proveedor actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/cupon/{id}")
    public ResponseEntity<String> eliminarCupon(@PathVariable int id) {
        boolean eliminado = conexionService.eliminarCupon(id);
        if (eliminado) {
            return ResponseEntity.ok("Cupon eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/proveedor/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable int id) {
        boolean eliminado = conexionService.eliminarProveedor(id);
        if (eliminado) {
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}   
