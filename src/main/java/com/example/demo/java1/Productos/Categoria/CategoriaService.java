package com.example.demo.java1.Productos.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategoriasConProductos() {
        return categoriaRepository.obtenerCategoriasConProductos();
    }
}

