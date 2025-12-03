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

    public List<Cupon> obtenerCupon() {
        String sql = "SELECT * FROM cupon";
        return jdbcTemplate.query(sql, new RowMapper<Cupon>() {
            @Override
            public Cupon mapRow(ResultSet rs, int rowNum) throws SQLException {
                Cupon c = new Cupon();
                c.setID_Cupon(rs.getInt("ID_Cupon"));
                c.setCodigo(rs.getString("Codigo"));
                c.setDescuento(rs.getInt("Descuento"));
                c.setFecha_Expiracion(rs.getDate("Fecha_Expiracion").toLocalDate());
                return c;
            }
        });
    }
    public void agregarCupon(Cupon cupon) {
        if (cupon.getCodigo() == null || cupon.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("El código del cupón no puede estar vacío");
        }

        String sql = "INSERT INTO cupon (Codigo, Descuento, Fecha_Expiracion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
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
