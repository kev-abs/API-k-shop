package com.example.demo.java1.Productos.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ControllerProductos {

    @Autowired
    private ServiceProductos conexionService;

    // Carpeta para guardar las imágenes dentro del proyecto
    private final String UPLOAD_DIR = System.getProperty("user.dir")
            + File.separator + "uploads" + File.separator + "productos";

    // Guardar imagen
    private String guardarImagen(MultipartFile imagen) throws IOException {
        if (imagen == null || imagen.isEmpty()) return null;

        // Crear carpeta si no existe
        File directorio = new File(UPLOAD_DIR);
        if (!directorio.exists()) directorio.mkdirs();

        // Generar nombre único para la imagen
        String nombreArchivo = UUID.randomUUID() + "_" +
                imagen.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-\\_]", "_");

        File destino = new File(directorio, nombreArchivo);
        imagen.transferTo(destino);

        // Ruta que Spring podrá servir
        return "/uploads/productos/" + nombreArchivo;
    }

    // Eliminar imagen existente
    private void eliminarImagenExistente(String rutaImagen) {
        if (rutaImagen == null || rutaImagen.isEmpty()) return;

        File archivo = new File(System.getProperty("user.dir") + rutaImagen.replace("/", File.separator));
        if (archivo.exists()) archivo.delete();
    }

    // Listar productos
    @GetMapping
    public List<Producto> listarProductos() {
        return conexionService.obtenerProductos();
    }

    // Crear producto
    @PostMapping("/crear")
    public ResponseEntity<String> crearProducto(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") double precio,
            @RequestParam("stock") int stock,
            @RequestParam("idProveedor") int idProveedor,
            @RequestParam("estado") String estado,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        try {
            String imagenNombre = guardarImagen(imagen);

            Producto nuevo = new Producto(nombre, descripcion, precio, stock, idProveedor, imagenNombre, estado);
            conexionService.insertarProducto(nuevo);

            return ResponseEntity.ok("Producto creado correctamente con imagen: " + imagenNombre);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error al guardar la imagen: " + e.getMessage());
        }
    }

    // Actualizar producto
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarProducto(
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
            Producto productoActual = conexionService.obtenerProductoPorId(id);
            if (productoActual == null) return ResponseEntity.status(404).body("No se encontró el producto");

            productoActual.setNombre(nombre);
            productoActual.setDescripcion(descripcion);
            productoActual.setPrecio(precio);
            productoActual.setStock(stock);
            productoActual.setID_Proveedor(idProveedor);
            productoActual.setEstado(estado);

            if (imagen != null && !imagen.isEmpty()) {
                eliminarImagenExistente(productoActual.getImagen());
                String nuevaImagen = guardarImagen(imagen);
                productoActual.setImagen(nuevaImagen);
            }

            int filas = conexionService.actualizarProducto(id, productoActual);
            return filas > 0
                    ? ResponseEntity.ok("Producto actualizado correctamente")
                    : ResponseEntity.status(500).body("No se pudo actualizar el producto");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error al actualizar producto: " + e.getMessage());
        }
    }

    // Eliminar producto
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        Producto producto = conexionService.obtenerProductoPorId(id);
        if (producto == null) return ResponseEntity.status(404).body("No se encontró el producto");

        eliminarImagenExistente(producto.getImagen());
        int filas = conexionService.eliminarProducto(id);
        return filas > 0
                ? ResponseEntity.ok("Producto eliminado correctamente")
                : ResponseEntity.status(500).body("No se pudo eliminar el producto");
    }
}
