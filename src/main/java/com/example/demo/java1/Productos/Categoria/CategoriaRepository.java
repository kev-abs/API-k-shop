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

    public List<Categoria> listarCategorias() {

        String sql = "SELECT id_categoria, nombre FROM categoria";

        return jdbc.query(sql, (rs, rowNum) -> {
            Categoria c = new Categoria();
            c.setIdCategoria(rs.getInt("id_categoria"));
            c.setNombre(rs.getString("nombre"));
            return c;
        });
    }

    public List<CategoriaConProductos> obtenerCategoriasConProductos() {

        String sql = """
        SELECT
                        c.id_categoria,
                        c.nombre AS categoria,
                        p.id_producto,
                        p.nombre AS producto,
                        p.precio
                    FROM categoria c
                    JOIN producto_categoria pc ON c.id_categoria = pc.id_categoria
                    JOIN producto p ON pc.id_producto = p.id_producto
    """;

        Map<Integer, CategoriaConProductos> mapa = new HashMap<>();

        jdbc.query(sql, rs -> {
            int idCategoria = rs.getInt("id_categoria");

            CategoriaConProductos categoria = mapa.get(idCategoria);
            if (categoria == null) {
                categoria = new CategoriaConProductos();
                categoria.setIdCategoria(idCategoria);
                categoria.setNombre(rs.getString("categoria"));
                categoria.setProductos(new ArrayList<>());
                mapa.put(idCategoria, categoria);
            }

            Producto producto = new Producto();
            producto.setID_Producto(rs.getInt("id_producto"));
            producto.setNombre(rs.getString("producto"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setImagen(rs.getString("imagen"));

            categoria.getProductos().add(producto);
        });

        return new ArrayList<>(mapa.values());
    }
}
