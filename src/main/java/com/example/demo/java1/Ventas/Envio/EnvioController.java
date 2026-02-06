package com.example.demo.java1.Ventas.Envio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/envio")
@CrossOrigin
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    // Cliente consulta estado
    @GetMapping("/pedido/{idPedido}")
    public Envio obtenerPorPedido(@PathVariable int idPedido) {
        return envioService.obtenerPorPedido(idPedido);
    }

    // Admin cambia estado
    @PutMapping("/{idEnvio}/estado/{estado}")
    public void cambiarEstado(
            @PathVariable int idEnvio,
            @PathVariable String estado) {

        envioService.cambiarEstado(idEnvio, estado);
    }
    @GetMapping
    public List<Envio> listarTodos() {
        return envioService.listarTodos();
    }

}

