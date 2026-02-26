package com.example.demo.java1.Productos.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listarCategorias();
    }
    public List<CategoriaConProductos> listarCategoriasConProductos() {
        return categoriaRepository.obtenerCategoriasConProductos();
    }
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.crearCategoria(categoria);
    }

    public Categoria actualizarCategoria(int id, Categoria categoria) {
        return categoriaRepository.actualizarCategoria(id, categoria);
    }

    public void eliminarCategoria(int id) {
        categoriaRepository.eliminarCategoria(id);
    }
}

