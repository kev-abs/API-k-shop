package com.example.demo.java1.Productos.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("/categorias")
    public ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok(
                categoriaService.listarCategorias()
        );
    }
    @GetMapping("/categorias-con-productos")
    public ResponseEntity<?> categoriasConProductos() {
        return ResponseEntity.ok(
                categoriaService.listarCategoriasConProductos()
        );
    }
}
