package com.example.demo.java1.Productos.ProductoCategoria;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/producto-categoria")
@CrossOrigin(origins = "*")
public class ProductoCategoriaController {

    @Autowired
    private ProductoCategoriaService service;

    @PostMapping("/asignar")
    public ResponseEntity<?> asignar(@RequestBody Map<String, Integer> body) {

        Integer idProducto = body.get("idProducto");
        Integer idCategoria = body.get("idCategoria");

        if (idProducto == null || idCategoria == null) {
            return ResponseEntity.badRequest().body("Datos incompletos");
        }

        service.asignarCategoria(idProducto, idCategoria);

        return ResponseEntity.ok("Asignado correctamente");
    }
}

