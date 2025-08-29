package com.example.demo.java1.Cupon_Cliente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConexionControllerCupon_Cliente {
    @Autowired
    private ConexionServiceCupon_Cliente conexionServiceCupon_cliente;

    @GetMapping("/cupon_cliente")
    public List<String> obtenerCupon_Cliente() {
        return conexionServiceCupon_cliente.obtenerCupon_Cliente();
    }

    @PostMapping("/cupon_cliente")
    public ResponseEntity<String> agregarCupon_Cliente(@RequestBody Cupon_Cliente cupon_cliente) {
        String resultado = conexionServiceCupon_cliente.agregarCupon_Cliente(cupon_cliente);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.badRequest().body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }
    @PutMapping("/cupon_cliente/{idCliente}/{idCupon}")
    public ResponseEntity<String> actualizarCupon_Cliente(
            @PathVariable int idCliente,
            @PathVariable int idCupon,
            @RequestBody Cupon_Cliente cupon_cliente) {

        String resultado = conexionServiceCupon_cliente.actualizarCupon_Cliente(idCliente, idCupon, cupon_cliente);

        if (resultado.startsWith("Error")) {
            return ResponseEntity.badRequest().body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }
}
