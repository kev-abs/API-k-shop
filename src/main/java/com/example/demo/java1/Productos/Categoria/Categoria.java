package com.example.demo.java1.Productos.Categoria;

import com.example.demo.java1.Productos.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private int idCategoria;
    private String nombre;
    private List<Producto> productos = new ArrayList<>();

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
