package com.example.demo.java1.Productos.ProductoCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @PostMapping("/asignar-multiple")
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
    public ResponseEntity<List<Map<String, Object>>> obtenerProductosPorCategoria() {
        return ResponseEntity.ok(service.obtenerProductosPorCategoria());
    }
}
