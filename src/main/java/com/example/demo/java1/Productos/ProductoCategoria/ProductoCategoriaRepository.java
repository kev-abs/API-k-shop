package com.example.demo.java1.Productos.ProductoCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoCategoriaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(int idProducto, int idCategoria) {

        String sql = """
            INSERT INTO producto_categoria (ID_Producto, ID_Categoria)
            VALUES (?, ?)
        """;

        jdbcTemplate.update(sql, idProducto, idCategoria);
    }
}
