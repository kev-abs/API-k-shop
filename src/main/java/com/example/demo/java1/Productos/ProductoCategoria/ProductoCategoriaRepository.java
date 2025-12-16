package com.example.demo.java1.Productos.ProductoCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductoCategoriaRepository {

    @Autowired
    private JdbcTemplate jdbc;
    public void eliminarPorProductos(List<Integer> productos) {
        String inSql = String.join(",", productos.stream().map(p -> "?").toList());

        jdbc.update(
                "DELETE FROM producto_categoria WHERE id_producto IN (" + inSql + ")",
                productos.toArray()
        );
    }

    public void insert(Integer idProducto, Integer idCategoria) {
        jdbc.update(
                "INSERT INTO producto_categoria (id_producto, id_categoria) VALUES (?, ?)",
                idProducto,
                idCategoria
        );
    }
    public List<Map<String, Object>> findProductosPorCategoria() {

        String sql = """
            SELECT
                c.id_categoria,
                c.nombre AS categoria,
                p.id_producto,
                p.nombre AS producto,
                p.descripcion,
                p.precio,
                p.imagen
            FROM categoria c
            JOIN producto_categoria pc ON c.id_categoria = pc.id_categoria
            JOIN producto p ON pc.id_producto = p.id_producto
            ORDER BY c.nombre, p.nombre
        """;

        return jdbc.queryForList(sql);
    }
}

