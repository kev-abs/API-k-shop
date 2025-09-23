package com.example.demo.java1.Productos.Resena;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConexionResena {
    private final JdbcTemplate jdbcTemplate;

    public ConexionResena(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertarResena(int idCliente, int idProducto, int calificacion, String comentario, LocalDate fecha) {
        String sql = "INSERT INTO resena (ID_Cliente, ID_Producto, Calificacion, Comentario, Fecha) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, idCliente, idProducto, calificacion, comentario, fecha);
    }

    public List<String> obtenerResenas() {
        String sql = "SELECT * FROM resena";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("ID_Resena") + " | " +
                        rs.getInt("ID_Cliente") + " | " +
                        rs.getInt("ID_Producto") + " | " +
                        rs.getInt("Calificacion") + " | " +
                        rs.getString("Comentario") + " | " +
                        rs.getDate("Fecha").toLocalDate();
            }
        });
    }

    public int actualizarResena(int idResena, int idCliente, int idProducto, int calificacion, String comentario, LocalDate fecha) {
        String sql = "UPDATE resena SET ID_Cliente = ?, ID_Producto = ?, Calificacion = ?, Comentario = ?, Fecha = ? WHERE ID_Resena = ?";
        return jdbcTemplate.update(sql, idCliente, idProducto, calificacion, comentario, fecha, idResena);
    }

    public int eliminarResena(int idReseña) {
        String sql = "DELETE FROM resena WHERE ID_Resena = ?";
        return jdbcTemplate.update(sql, idReseña);
    }



}
