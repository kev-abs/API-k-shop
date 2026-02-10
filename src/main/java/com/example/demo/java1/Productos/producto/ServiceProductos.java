package com.example.demo.java1.Productos.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Service
public class ServiceProductos {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obtener lista de productos
    public List<Producto> obtenerProductos() {
        String sql = "SELECT * FROM producto";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Producto.class));
    }

    // Obtener producto por ID
    public Producto obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM producto WHERE ID_Producto = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Producto p = new Producto();
            p.setID_Producto(rs.getInt("ID_Producto"));
            p.setNombre(rs.getString("Nombre"));
            p.setDescripcion(rs.getString("Descripcion"));
            p.setPrecio(rs.getDouble("Precio"));
            p.setStock(rs.getInt("Stock"));
            p.setID_Proveedor(rs.getInt("ID_Proveedor"));
            p.setImagen(rs.getString("Imagen"));
            p.setEstado(rs.getString("Estado"));
            return p;
        });
    }


    // Insertar producto (sin imagen binaria, solo nombre)
    public void insertarProducto(Producto producto) {

        String sql = "INSERT INTO producto (Nombre, Descripcion, Precio, Stock, ID_Proveedor, Imagen, Estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getID_Proveedor());
            ps.setString(6, producto.getImagen());
            ps.setString(7, producto.getEstado());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            producto.setID_Producto(keyHolder.getKey().intValue());
        }
    }

    // Actualizar producto
    public int actualizarProducto(Producto producto, int id) {
        String sql = "UPDATE producto SET Nombre=?, Descripcion=?, Precio=?, Stock=?, ID_Proveedor=?, Imagen=?, Estado=? " +
                "WHERE ID_Producto=?";

        return jdbcTemplate.update(sql,
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getID_Proveedor(),
                producto.getImagen(),
                producto.getEstado(),
                id
        );
    }

    // Eliminar producto
    public int eliminarProducto(int id) {
        String sql = "DELETE FROM producto WHERE ID_Producto = ?";
        return jdbcTemplate.update(sql, id);
    }
}
