package com.example.demo.java1.Productos.ProductoCategoria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Tag(name = "ProductosCategoria", description = "Operaciones sobre la tabla productos_categoria")
@RestController
@RequestMapping("/api/producto-categoria")
@CrossOrigin(origins = "*")
public class ProductoCategoriaController {

    @Autowired
    private ProductoCategoriaService service;
    @PostMapping("/asignar")
    @Operation(summary = " Categorias",
            description = "Permite categorizar un producto")
    public ResponseEntity<?> asignar(@RequestBody Map<String, Integer> body) {

        Integer idProducto = body.get("idProducto");
        Integer idCategoria = body.get("idCategoria");

        if (idProducto == null || idCategoria == null) {
            return ResponseEntity.badRequest().body("Datos incompletos");
        }

        service.asignarCategoria(idProducto, idCategoria);
        return ResponseEntity.ok("Asignado correctamente");
    }
    @PostMapping("/asignar-multiple")
    @Operation(summary = "Asignar categorias",
            description = "Permite categorizar uno o varios productos")
    public ResponseEntity<?> asignarMultiple(@RequestBody Map<String, Object> body) {

        Integer idCategoria = (Integer) body.get("idCategoria");
        List<Integer> productos = (List<Integer>) body.get("productos");

        if (idCategoria == null || productos == null || productos.isEmpty()) {
            return ResponseEntity.badRequest().body("Datos incompletos");
        }
        service.eliminarCategoriasDeProductos(productos);

        // asigna nueva categoría
        for (Integer idProducto : productos) {
            service.asignarCategoria(idProducto, idCategoria);
        }

        return ResponseEntity.ok("Productos categorizados correctamente");
    }

    @GetMapping("/por-categoria")
    @Operation(summary = "Obtener Productos por Categoria",
            description = "Permite obteenr los productos organizados y en su respectiva categoria")
    public ResponseEntity<List<Map<String, Object>>> obtenerProductosPorCategoria() {
        return ResponseEntity.ok(service.obtenerProductosPorCategoria());
    }
}
