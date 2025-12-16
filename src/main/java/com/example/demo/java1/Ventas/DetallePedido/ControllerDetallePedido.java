package com.example.demo.java1.Ventas.DetallePedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "DetallePedido", description = "Detalles de la tabla pedidos")
@RequestMapping("/detalle")
public class ControllerDetallePedido {

    @Autowired
    private ServiceDetallePedido serviceDetallePedido;

    @GetMapping
    @Operation(
            summary = "Obtener detalles de pedidos",
            description = "Permite obtener el listado de todos los detalles asociados a los pedidos registrados"
    )
    public List<String> obtenerDetalles() {
        return serviceDetallePedido.obtenerDetalles();
    }


    @PostMapping
    @Operation(
            summary = "Agregar detalle de pedido",
            description = "Permite registrar un nuevo detalle de pedido asociando producto, cantidad y precio unitario"
    )
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
    @Operation(
            summary = "Actualizar detalle de pedido",
            description = "Permite actualizar la información de un detalle de pedido existente mediante su identificador"
    )
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


        @DeleteMapping("/detalle/{id}")
        @Operation(
                summary = "Eliminar detalle de pedido",
                description = "Permite eliminar un detalle de pedido del sistema mediante su identificador"
        )
        public ResponseEntity<String> eliminarDetalle ( @PathVariable("id") int idDetalle){
            int filas = serviceDetallePedido.eliminarDetalle(idDetalle);
            if (filas > 0) {
                return ResponseEntity.ok("Detalle eliminado correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}