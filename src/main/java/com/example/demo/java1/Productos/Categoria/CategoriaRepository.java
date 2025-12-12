package com.example.demo.java1.Productos.Categoria;

import com.example.demo.java1.Productos.producto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CategoriaRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Categoria> obtenerCategoriasConProductos() {

        String sql = """
            SELECT c.id_categoria, c.nombre AS categoria,
                   p.id_producto, p.nombre AS producto, p.precio
            FROM categoria c
            JOIN producto_categoria pc ON c.id_categoria = pc.id_categoria
            JOIN producto p ON pc.id_producto = p.id_producto
            WHERE p.estado = 'Disponible'
        """;

        Map<Integer, Categoria> mapa = new HashMap<>();

        jdbc.query(sql, rs -> {
            int idCategoria = rs.getInt("id_categoria");

            Categoria categoria = mapa.get(idCategoria);
            if (categoria == null) {
                categoria = new Categoria();
                categoria.setIdCategoria(idCategoria);
                categoria.setNombre(rs.getString("categoria"));
                categoria.setProductos(new ArrayList<>());
                mapa.put(idCategoria, categoria);
            }

            Producto producto = new Producto();
            producto.setID_Producto(rs.getInt("id_producto"));
            producto.setNombre(rs.getString("producto"));
            producto.setPrecio(rs.getDouble("precio"));

            categoria.getProductos().add(producto);
        });

        return new ArrayList<>(mapa.values());
    }
}
