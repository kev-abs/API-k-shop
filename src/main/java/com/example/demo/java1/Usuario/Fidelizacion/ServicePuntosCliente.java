package com.example.demo.java1.Usuario.Fidelizacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicePuntosCliente {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Listar puntos de clientes
    public List<String> obtenerPuntosClientes() {
        String sql = "SELECT * FROM puntos_cliente";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("ID_Puntos") + " | " +
                        rs.getInt("ID_Cliente") + " | " +
                        rs.getInt("Puntos") + " | " +
                        rs.getTimestamp("Fecha_Actualizacion").toLocalDateTime();
            }
        });
    }

    // Insertar registro de puntos
    public int insertarPuntosCliente(Integer idCliente, Integer puntos) {
        String sql = "INSERT INTO puntos_cliente (ID_Cliente, Puntos) VALUES (?, ?)";
        return jdbcTemplate.update(sql, idCliente, puntos);
    }

    // Actualizar puntos de un cliente
    public int actualizarPuntosCliente(int idPuntos, Integer idCliente, Integer puntos) {
        String sql = "UPDATE puntos_cliente SET ID_Cliente=?, Puntos=?, Fecha_Actualizacion=CURRENT_TIMESTAMP " +
                "WHERE ID_Puntos=?";
        return jdbcTemplate.update(sql, idCliente, puntos, idPuntos);
    }

    // Eliminar registro de puntos
    public int eliminarPuntosCliente(int idPuntos) {
        String sql = "DELETE FROM puntos_cliente WHERE ID_Puntos = ?";
        return jdbcTemplate.update(sql, idPuntos);
    }
}
