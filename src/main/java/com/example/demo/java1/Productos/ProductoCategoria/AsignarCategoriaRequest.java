package com.example.demo.java1.Productos.ProductoCategoria;

import java.util.List;

public class AsignarCategoriaRequest {

    private Integer idCategoria;
    private List<Integer> productos;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<Integer> getProductos() {
        return productos;
    }

    public void setProductos(List<Integer> productos) {
        this.productos = productos;
    }
}
