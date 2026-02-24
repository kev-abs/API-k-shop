package com.example.demo.java1.Productos.Categoria;

import com.example.demo.java1.Productos.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private Integer idCategoria;
    private String nombre;

    public Categoria() {}

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
