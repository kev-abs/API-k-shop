package com.example.demo.java1.Usuario.ListaDeseos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConexionListaDeseos {
    private final JdbcTemplate jdbcTemplate;

    public ConexionListaDeseos(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    public int insertarListaDeseos(int idCliente, LocalDate fechaCreacion) {
        String sql = "INSERT INTO lista_deseos (ID_Cliente, Fecha_Creacion) VALUES (?, ?)";
        return jdbcTemplate.update(sql, idCliente, fechaCreacion);
    }

    // READ
    public List<ListaDeseos> obtenerListasDeseos() {
        String sql = "SELECT * FROM lista_deseos";
        return jdbcTemplate.query(sql, new RowMapper<ListaDeseos>() {
            @Override
            public ListaDeseos mapRow(ResultSet rs, int rowNum) throws SQLException {
                ListaDeseos lista = new ListaDeseos();
                lista.setIdLista(rs.getInt("ID_Lista"));
                lista.setIdCliente(rs.getInt("ID_Cliente"));
                lista.setFechaCreacion(rs.getDate("Fecha_Creacion").toLocalDate());
                return lista;
            }
        });
    }

    // UPDATE
    public int actualizarListaDeseos(int idLista, int idCliente, LocalDate fechaCreacion) {
        String sql = "UPDATE lista_deseos SET ID_Cliente = ?, Fecha_Creacion = ? WHERE ID_Lista = ?";
        return jdbcTemplate.update(sql, idCliente, fechaCreacion, idLista);
    }

    // DELETE
    public int eliminarListaDeseos(int idLista) {
        String sql = "DELETE FROM lista_deseos WHERE ID_Lista = ?";
        return jdbcTemplate.update(sql, idLista);
    }
}
