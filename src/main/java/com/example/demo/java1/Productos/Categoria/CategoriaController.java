package com.example.demo.java1.Productos.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/categorias")
    @Operation(summary = "Crear Categoria",
            description = "Permite crear una nueva categoria")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio");
        }
        return ResponseEntity.ok(categoriaService.crearCategoria(categoria));
    }

    @PutMapping("/categorias/{id}")
    @Operation(summary = "Actualizar Categoria",
            description = "Permite actualizar una categoria existente")
    public ResponseEntity<?> actualizarCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio");
        }
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categoria));
    }

    @DeleteMapping("/categorias/{id}")
    @Operation(summary = "Eliminar Categoria",
            description = "Permite eliminar una categoria")
    public ResponseEntity<?> eliminarCategoria(@PathVariable int id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("Categoría eliminada correctamente");
    }
}
