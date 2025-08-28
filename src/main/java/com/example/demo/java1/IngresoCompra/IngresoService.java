package com.example.demo.java1.IngresoCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class IngresoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> obtenerIngreso() {
        String sql = "SELECT * FROM ingreso_compra";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("ID_Proveedor") + "   " +
                        rs.getString("Total");
            }
        });
    }

    public boolean actualizarIngreso(int id, IngresoCompra ingresoCompra) {
        String sql = "UPDATE ingreso_compra " +
                "SET ID_Empleado = ?, ID_Proveedor = ?, Fecha_Ingreso = ?, Total = ? " +
                "WHERE ID_ingreso = ?";
        int rows = jdbcTemplate.update(sql,
                ingresoCompra.getID_Empleado(),
                ingresoCompra.getID_Proveedor(),
                ingresoCompra.getFecha_Ingreso(),
                ingresoCompra.getTotal(),
                id
        );
        return rows > 0;
    }

    public boolean eliminarIngreso(int id) {
        String sql = "DELETE FROM ingreso_compra WHERE ID_Ingreso = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}

