package com.example.demo.java1.Ventas.Carrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void agregarAlCarrito(Carrito carrito) {
        String sql = """
            INSERT INTO carrito (ID_Cliente, ID_Producto, cantidad)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE cantidad = cantidad + ?
        """;

        jdbcTemplate.update(
                sql,
                carrito.getIdCliente(),
                carrito.getIdProducto(),
                carrito.getCantidad(),
                carrito.getCantidad()
        );
    }

    // Obtener carrito por cliente
    public List<Carrito> obtenerCarrito(int idCliente) {
        String sql = "SELECT * FROM carrito WHERE ID_Cliente = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Carrito c = new Carrito();
            c.setIdCarrito(rs.getInt("ID_Carrito"));
            c.setIdCliente(rs.getInt("ID_Cliente"));
            c.setIdProducto(rs.getInt("ID_Producto"));
            c.setCantidad(rs.getInt("cantidad"));
            return c;
        }, idCliente);
    }

    // Vaciar carrito
    public void vaciarCarrito(int idCliente) {
        jdbcTemplate.update(
                "DELETE FROM carrito WHERE ID_Cliente = ?",
                idCliente
        );
    }
}
