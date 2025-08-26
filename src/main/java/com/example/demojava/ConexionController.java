package com.example.demojava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConexionController {
    @Autowired
    private ConexionService conexionService;

    @GetMapping("/cliente")
    public List<String> obtenerCliente() {
        return conexionService.obtenerCliente();
    }

    @GetMapping("/producto")
    public List<String> obtenerProducto() {
        return conexionService.obtenerProducto();
    }

    @PostMapping("/cliente")
    public String agregarcliente(@RequestBody Cliente cliente) {
        conexionService.agregarCliente(cliente);
        return "Cliente agregado";
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        boolean actualizado = conexionService.actualizarCliente(id, cliente);
        if (actualizado) {
            return ResponseEntity.ok("Cliente actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable int id) {
        boolean eliminado = conexionService.eliminarCliente(id);
        if (eliminado) {
            return ResponseEntity.ok("Cliente eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}