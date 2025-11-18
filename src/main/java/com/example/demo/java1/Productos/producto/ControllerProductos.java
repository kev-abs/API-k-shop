package com.example.demo.java1.Productos.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ControllerProductos {

    @Autowired
    private ServiceProductos conexionService;

    // ============================================================
    //                MÉTODO PARA GUARDAR IMAGEN
    // ============================================================
    private String guardarImagen(MultipartFile imagen) throws IOException {
        if (imagen == null || imagen.isEmpty()) return null;

        String uploadDir = "uploads/productos/";
        File directorio = new File(uploadDir);
        if (!directorio.exists()) directorio.mkdirs();

        String nombreImagen = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
        Path ruta = Paths.get(uploadDir + nombreImagen);

        Files.copy(imagen.getInputStream(), ruta);

        return nombreImagen;
    }

    // ============================================================
    //                ELIMINAR IMAGEN DEL SERVIDOR
    // ============================================================
    private void eliminarImagenExistente(String nombreImagen) {
        if (nombreImagen == null) return;

        Path ruta = Paths.get("uploads/productos/" + nombreImagen);
        try {
            Files.deleteIfExists(ruta);
        } catch (IOException ignored) {}
    }


    // ============================================================
    //                       INSERTAR PRODUCTO
    // ============================================================
    @PostMapping("/insertar")
    public ResponseEntity<?> insertarProducto(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam("stock") int stock,
            @RequestParam("idProveedor") int idProveedor,
            @RequestParam("estado") String estado,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        try {
            String nombreImagen = null;

            if (imagen != null && !imagen.isEmpty()) {
                nombreImagen = guardarImagen(imagen);
            }

            Producto nuevo = new Producto();
            nuevo.setNombre(nombre);
            nuevo.setDescripcion(descripcion);
            nuevo.setPrecio(precio);
            nuevo.setStock(stock);
            nuevo.setID_Proveedor(idProveedor);
            nuevo.setEstado(estado);
            nuevo.setImagen(nombreImagen);

            conexionService.insertarProducto(nuevo);

            return ResponseEntity.ok("Producto insertado con éxito");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al insertar: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return conexionService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable int id) {
        Producto p = conexionService.obtenerProductoPorId(id);
        if (p == null)
            return ResponseEntity.status(404).body("Producto no encontrado");

        return ResponseEntity.ok(p);
    }

    @PostMapping("actualizar/{id}")
    public ResponseEntity<?> actualizarProducto(
            @PathVariable int id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") double precio,
            @RequestParam("stock") int stock,
            @RequestParam("idProveedor") int idProveedor,
            @RequestParam("estado") String estado,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        try {
            Producto p = conexionService.obtenerProductoPorId(id);
            if (p == null)
                return ResponseEntity.status(404).body("Producto no encontrado");

            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
            p.setStock(stock);
            p.setID_Proveedor(idProveedor);
            p.setEstado(estado);

            if (imagen != null && !imagen.isEmpty()) {
                eliminarImagenExistente(p.getImagen());
                String nuevaImagen = guardarImagen(imagen);
                p.setImagen(nuevaImagen);
            }

            conexionService.actualizarProducto(p, id);

            return ResponseEntity.ok("Producto actualizado con éxito");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al actualizar: " + e.getMessage());
        }
    }


    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) {

        Producto p = conexionService.obtenerProductoPorId(id);
        if (p == null)
            return ResponseEntity.status(404).body("Producto no encontrado");

        eliminarImagenExistente(p.getImagen());
        conexionService.eliminarProducto(id);

        return ResponseEntity.ok("Producto eliminado con éxito");
    }
}

