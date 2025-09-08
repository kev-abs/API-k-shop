package com.example.demo.java1.Inventario.Cupon;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class ConexionServiceCupon {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> obtenerCupon() {
        String sql = "SELECT * FROM cupon";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("ID_Cupon") + " " +
                        rs.getString("Codigo") + " " +
                        rs.getString("Descuento") + " "+
                        rs.getString("Fecha_Expiracion");
            }
        });

    }
    public void agregarCupon(Cupon cupon) {
        String sql = "INSERT INTO cupon" +
                "(ID_Cupon, Codigo, Descuento, Fecha_Expiracion)" + "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                cupon.getID_Cupon(),
                cupon.getCodigo(),
                cupon.getDescuento(),
                cupon.getFecha_Expiracion()
        );
    }

    public boolean actualizarCupon(int id, Cupon cupon) {
        String sql = "UPDATE cupon SET Codigo = ?, Descuento = ?, Fecha_Expiracion = ? WHERE ID_Cupon = ?";
        int filasAfectadas = jdbcTemplate.update(sql,
                cupon.getCodigo(),
                cupon.getDescuento(),
                cupon.getFecha_Expiracion(),
                id
        );
        return filasAfectadas > 0;
    }

    public boolean eliminarCupon(int id) {
        String sql = "DELETE FROM cupon WHERE ID_Cupon = ?";
        int filasAfectadas = jdbcTemplate.update(sql, id);
        return filasAfectadas > 0;
    }
}
