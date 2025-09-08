package com.example.demo.java1.Usuario.Listadeseosproducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lista-deseos")
public class ControllerListaDeseosProducto {

    @Autowired
    private ServiceListaDeseosProducto listaDeseosService;

    @GetMapping("")
    public List<String> listarDeseos() {
        return listaDeseosService.obtenerListaDeseos();
    }

    @PostMapping("")
    public String crearDeseo(@RequestBody ListaDeseosProductoDTO listaDeseo) {
        int filas = listaDeseosService.insertarListaDeseos(
                listaDeseo.getIdLista(),
                listaDeseo.getIdProducto()
        );
        return (filas > 0) ? "Producto agregado a la lista de deseos" : "Error al agregar";
    }

    @PutMapping("/{id}")
    public String actualizarDeseo(@PathVariable int id, @RequestBody ListaDeseosProductoDTO listaDeseo) {
        int filas = listaDeseosService.actualizarListaDeseos(
                id,
                listaDeseo.getIdProducto()
        );
        return (filas > 0) ? "Lista de deseos actualizada" : "Registro no encontrado o sin cambios";
    }

    @DeleteMapping("/{id}")
    public String eliminarDeseo(@PathVariable int id) {
        int filas = listaDeseosService.eliminarListaDeseos(id);
        return (filas > 0) ? "Registro eliminado de la lista de deseos" : "Registro no encontrado";
    }
}
