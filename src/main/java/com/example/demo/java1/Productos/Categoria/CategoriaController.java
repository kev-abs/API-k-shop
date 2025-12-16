package com.example.demo.java1.Productos.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
@Tag(name = "Categorias", description = "categorizacion de los productos")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("/categorias")
    @Operation(summary = "Listar Categorias",
            description = "Permite obtener las categorias actualmente activas")
    public ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok(
                categoriaService.listarCategorias()
        );
    }
    @GetMapping("/categorias-con-productos")
    @Operation(summary = " Categorias con productos",
            description = "Permite consultas a que categoria pertenece cada producto")
    public ResponseEntity<?> categoriasConProductos() {
        return ResponseEntity.ok(
                categoriaService.listarCategoriasConProductos()
        );
    }
}
