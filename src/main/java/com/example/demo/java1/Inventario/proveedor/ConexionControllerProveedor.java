package com.example.demo.java1.Inventario.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConexionControllerProveedor {
    @Autowired
    private ConexionServiceProveedor conexionServiceProveedor;



    @GetMapping("/proveedor")
    public List<Proveedor> obtenerProveedor() {
        return conexionServiceProveedor.obtenerProveedor();
    }

    @PostMapping("/proveedor")
    public String agregarProveedor(@RequestBody Proveedor proveedor) {
        conexionServiceProveedor.agregarProveedor(proveedor);
        return "Proveedor agregado";
    }




    @PutMapping("/proveedor/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        boolean actualizado = conexionServiceProveedor.actualizarProveedor(id,proveedor);
        if (actualizado) {
            return ResponseEntity.ok("Proveedor actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @DeleteMapping("/proveedor/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable int id) {
        boolean eliminado = conexionServiceProveedor.eliminarProveedor(id);
        if (eliminado) {
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}   
