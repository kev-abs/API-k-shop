package com.example.demo.java1.Ventas.Envio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnvioService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Consultar envío por pedido
    public Envio obtenerPorPedido(int idPedido) {

        String sql = "SELECT * FROM envio WHERE ID_Pedido = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Envio e = new Envio();
            e.setIdEnvio(rs.getInt("ID_Envio"));
            e.setIdPedido(rs.getInt("ID_Pedido"));
            e.setDireccion(rs.getString("direccion"));
            e.setCiudad(rs.getString("ciudad"));
            e.setEstado(rs.getString("estado"));
            e.setFechaCreacion(
                    rs.getTimestamp("fecha_creacion").toLocalDateTime()
            );
            return e;
        }, idPedido);
    }

    // Cambiar estado (admin)
    public void cambiarEstado(int idEnvio, String estado) {

        jdbcTemplate.update(
                "UPDATE envio SET estado = ? WHERE ID_Envio = ?",
                estado,
                idEnvio
        );
    }
    public List<Envio> listarTodos() {

        String sql = """
        SELECT * FROM envio
        ORDER BY ID_Envio DESC
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Envio e = new Envio();
            e.setIdEnvio(rs.getInt("ID_Envio"));
            e.setIdPedido(rs.getInt("ID_Pedido"));
            e.setDireccion(rs.getString("direccion"));
            e.setCiudad(rs.getString("ciudad"));
            e.setEstado(rs.getString("estado"));
            return e;
        });
    }

}

