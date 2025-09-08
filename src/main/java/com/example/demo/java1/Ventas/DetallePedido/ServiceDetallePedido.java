package com.example.demo.java1.Ventas.DetallePedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ServiceDetallePedido {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obtener lista de Detalles de pedido
    public List<String> obtenerDetalles() {
        String sql = "SELECT * FROM detalle_pedido";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("ID_Detalle") + " | " +
                        rs.getInt("ID_Pedido") + " | " +
                        rs.getInt("ID_Producto") + " | " +
                        rs.getInt("Cantidad") + " | " +
                        rs.getDouble("Precio_Unitario");
            }
        });
    }

    // Insertar detalle
    public int insertarDetalle(int idPedido, int idProducto, int cantidad, double precioUnitario) {
        String sql = "INSERT INTO detalle_pedido (ID_Pedido, ID_Producto, Cantidad, Precio_Unitario) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, idPedido, idProducto, cantidad, precioUnitario);
    }

    // Actualizar detalle
    public int actualizarDetalle(int idDetalle, int idPedido, int idProducto, int cantidad, double precioUnitario) {
        String sql = "UPDATE detalle_pedido SET ID_Pedido=?, ID_Producto=?, Cantidad=?, Precio_Unitario=? " +
                "WHERE ID_Detalle=?";
        return jdbcTemplate.update(sql, idPedido, idProducto, cantidad, precioUnitario, idDetalle);
    }

    // Eliminar detalle
    public int eliminarDetalle(int idDetalle) {
        String sql = "DELETE FROM detalle_pedido WHERE ID_Detalle=?";
        return jdbcTemplate.update(sql, idDetalle);
    }
}
