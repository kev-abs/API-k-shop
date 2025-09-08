package com.example.demo.java1.Usuario.Listadeseosproducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ServiceListaDeseosProducto {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> obtenerListaDeseos() {
        String sql = "SELECT * FROM lista_deseos_producto";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("ID_Lista") + " | " +
                       rs.getInt("ID_Producto");
            }
        });
    }

    public int insertarListaDeseos(Integer idLista, Integer idProducto) {
        String sql = "INSERT INTO lista_deseos_producto (ID_Lista, ID_Producto) VALUES (?, ?)";
        return jdbcTemplate.update(sql, idLista, idProducto);
    }

    public int actualizarListaDeseos(int idLista, Integer idProducto) {
        String sql = "UPDATE lista_deseos_producto SET ID_Producto=? WHERE ID_Lista=?";
        return jdbcTemplate.update(sql, idProducto, idLista);
    }

    public int eliminarListaDeseos(int idLista) {
        String sql = "DELETE FROM lista_deseos_producto WHERE ID_Lista=?";
        return jdbcTemplate.update(sql, idLista);
    }
}
