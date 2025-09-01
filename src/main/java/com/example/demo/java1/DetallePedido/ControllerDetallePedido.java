package com.example.demo.java1.DetallePedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle")
public class ControllerDetallePedido {

    @Autowired
    private com.example.demo.java1.DetallePedido.ServiceDetallePedido serviceDetallePedido;

    @GetMapping
    public List<String> obtenerDetalles() {
        return serviceDetallePedido.obtenerDetalles();
    }

    // POST: insertar detalle
    @PostMapping
    public ResponseEntity<String> insertarDetalle(@RequestBody DetallePedido detalle) {
        int filas = serviceDetallePedido.insertarDetalle(
                detalle.getID_Pedido(),
                detalle.getID_Producto(),
                detalle.getCantidad(),
                detalle.getPrecio_Unitario()
        );
        if (filas > 0) {
            return ResponseEntity.ok("Detalle insertado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al insertar detalle");
        }
    }

    @PutMapping("/detalle/{id}")
    public ResponseEntity<String> actualizarDetalle(@PathVariable int id, @RequestBody DetallePedido detalle) {
        int filas = serviceDetallePedido.actualizarDetalle(
                id,
                detalle.getID_Pedido(),
                detalle.getID_Producto(),
                detalle.getCantidad(),
                detalle.getPrecio_Unitario()
        );
        if (filas > 0) {
            return ResponseEntity.ok("Detalle actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        // DELETE: eliminar detalle
        @DeleteMapping("/detalle/{id}")
        public ResponseEntity<String> eliminarDetalle ( @PathVariable int idDetalle){
            int filas = serviceDetallePedido.eliminarDetalle(idDetalle);
            if (filas > 0) {
                return ResponseEntity.ok("Detalle eliminado correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}