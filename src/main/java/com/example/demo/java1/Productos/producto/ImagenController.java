package com.example.demo.java1.Productos.producto;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImagenController {

    @GetMapping("/uploads/productos/{nombre}")
    public ResponseEntity<Resource> verImagen(@PathVariable String nombre) throws IOException {

        Path ruta = Paths.get("C:/xampp/htdocs/API/uploads/productos/").resolve(nombre);
        Resource recurso = new UrlResource(ruta.toUri());

        if (!recurso.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(ruta))
                .body(recurso);
    }
}

