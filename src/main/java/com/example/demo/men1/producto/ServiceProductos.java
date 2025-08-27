package com.example.demo.men1.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ServiceProductos {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obtener lista de productos
    public List<String> obtenerProductos() {
        String sql = "SELECT * FROM producto";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("ID_Producto") + " | " +
                        rs.getString("Nombre") + " | " +
                        rs.getString("Descripcion") + " | " +
                        rs.getDouble("Precio") + " | Stock: " +
                        rs.getInt("Stock") + " | " +
                        rs.getInt("ID_Proveedor") + " | " +
                        rs.getString("Imagen") + " | " +
                        rs.getString("Estado");
            }
        });
    }

    // Insertar producto
    public int insertarProducto(Producto producto) {
        String sql = "INSERT INTO producto (Nombre, Descripcion, Precio, Stock, ID_Proveedor, Imagen, Estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getIdProveedor(),
                producto.getImagen(),
                producto.getEstado()
        );
    }

    // Actualizar producto
    public int actualizarProducto(int id, Producto producto) {
        String sql = "UPDATE producto SET Nombre=?, Descripcion=?, Precio=?, Stock=?, ID_Proveedor=?, Imagen=?, Estado=? " +
                "WHERE ID_Producto=?";
        return jdbcTemplate.update(sql,
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getIdProveedor(),
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
