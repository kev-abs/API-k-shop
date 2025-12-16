package com.example.demo.java1.Productos.Categoria;

import com.example.demo.java1.Productos.producto.Producto;

import java.util.List;

public class CategoriaProductosDTO {

    private Integer idCategoria;
    private String nombreCategoria;
    private List<Producto> productos;

    public CategoriaProductosDTO() {}

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

