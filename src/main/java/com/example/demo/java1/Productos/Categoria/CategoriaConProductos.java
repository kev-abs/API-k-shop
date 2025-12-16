package com.example.demo.java1.Productos.Categoria;


import com.example.demo.java1.Productos.producto.Producto;
import java.util.List;

public class CategoriaConProductos {

    private Integer idCategoria;
    private String nombre;
    private List<Producto> productos;

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
