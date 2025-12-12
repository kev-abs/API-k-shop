package com.example.demo.java1.Productos.ProductoCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoCategoriaService {

    @Autowired
    private ProductoCategoriaRepository repository;

    public void asignarCategoria(int idProducto, int idCategoria) {
        repository.insert(idProducto, idCategoria);
    }
}

