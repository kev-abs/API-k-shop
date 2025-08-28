package com.example.demo.java1.Reseña;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConexionReseña {
    private final JdbcTemplate jdbcTemplate;

    public ConexionReseña(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertarReseña(int idCliente, int idProducto, int calificacion, String comentario, LocalDate fecha) {
        String sql = "INSERT INTO reseña (ID_Cliente, ID_Producto, Calificacion, Comentario, Fecha) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, idCliente, idProducto, calificacion, comentario, fecha);
    }

    public List<Reseña> obtenerReseñas() {
        String sql = "SELECT * FROM reseña";
        return jdbcTemplate.query(sql, new RowMapper<Reseña>() {
            @Override
            public Reseña mapRow(ResultSet rs, int rowNum) throws SQLException {
                Reseña reseña = new Reseña();
                reseña.setIdResena(rs.getInt("ID_Resena"));
                reseña.setIdCliente(rs.getInt("ID_Cliente"));
                reseña.setIdProducto(rs.getInt("ID_Producto"));
                reseña.setCalificacion(rs.getInt("Calificacion"));
                reseña.setComentario(rs.getString("Comentario"));
                reseña.setFecha(rs.getDate("Fecha").toLocalDate());
                return reseña;
            }
        });
    }

    public int actualizarReseña(int idReseña, int idCliente, int idProducto, int calificacion, String comentario, LocalDate fecha) {
        String sql = "UPDATE reseña SET ID_Cliente = ?, ID_Producto = ?, Calificacion = ?, Comentario = ?, Fecha = ? WHERE ID_Resena = ?";
        return jdbcTemplate.update(sql, idCliente, idProducto, calificacion, comentario, fecha, idReseña);
    }

    public int eliminarReseña(int idReseña) {
        String sql = "DELETE FROM reseña WHERE ID_Resena = ?";
        return jdbcTemplate.update(sql, idReseña);
    }
}
