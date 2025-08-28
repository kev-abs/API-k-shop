package com.example.demo.java1.Envio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ConexionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obtener todos los envíos
    public List<Envio> obtenerEnvios() {
        String sql = "SELECT * FROM envio";
        return jdbcTemplate.query(sql, new RowMapper<Envio>() {
            @Override
            public Envio mapRow(ResultSet rs, int rowNum) throws SQLException {
                Envio envio = new Envio();
                envio.setID_Envio(rs.getInt("ID_Envio"));
                envio.setID_Pedido(rs.getInt("ID_Pedido"));
                envio.setDireccionEnvio(rs.getString("direccion_envio"));
                envio.setFechaEnvio(rs.getString("fecha_envio"));
                envio.setMetodoEnvio(rs.getString("metodo_envio"));
                envio.setEstadoEnvio(rs.getString("estado_envio"));
                return envio;
            }
        });
    }

    // Agregar un envío
    public void agregarEnvio(Envio envio) {
        String sql = "INSERT INTO envio (ID_Pedido, direccion_envio, fecha_envio, metodo_envio, estado_envio) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                envio.getID_Pedido(),
                envio.getDireccionEnvio(),
                envio.getFechaEnvio(),
                envio.getMetodoEnvio(),
                envio.getEstadoEnvio()
        );
    }

    // Actualizar un envío
    public boolean actualizarEnvio(int id, Envio envio) {
        String sql = "UPDATE envio SET ID_Pedido = ?, direccion_envio = ?, fecha_envio = ?, metodo_envio = ?, estado_envio = ? WHERE ID_Envio = ?";
        int filasAfectadas = jdbcTemplate.update(sql,
                envio.getID_Pedido(),
                envio.getDireccionEnvio(),
                envio.getFechaEnvio(),
                envio.getMetodoEnvio(),
                envio.getEstadoEnvio(),
                id
        );
        return filasAfectadas > 0;
    }

    // Eliminar un envío
    public boolean eliminarEnvio(int id) {
        String sql = "DELETE FROM envio WHERE ID_Envio = ?";
        int filasAfectadas = jdbcTemplate.update(sql, id);
        return filasAfectadas > 0;
    }
}
