package com.example.demo.java1.Productos.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
        List<Producto> productos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Producto.class), id);
        return productos.isEmpty() ? null : productos.get(0);
    }

    // Insertar producto (sin imagen binaria, solo nombre)
    public void insertarProducto(Producto producto) {
        String sql = "INSERT INTO producto (Nombre, Descripcion, Precio, Stock, ID_Proveedor, Imagen, Estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getID_Proveedor(),
                producto.getImagen(),  // nombre del archivo.jpg
                producto.getEstado()
        );
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
                producto.getIdProducto()   // <-- AQUI EL ERROR QUE TE SALIA
        );
    }

    // Eliminar producto
    public int eliminarProducto(int id) {
        String sql = "DELETE FROM producto WHERE ID_Producto = ?";
        return jdbcTemplate.update(sql, id);
    }
}
