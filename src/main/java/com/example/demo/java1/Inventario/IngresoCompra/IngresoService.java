package com.example.demo.java1.Inventario.IngresoCompra;

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

    // Obtener lista de ingresos
    public List<String> obtenerIngreso() {
        String sql = "SELECT * FROM ingreso_compra";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("ID_Ingreso") + " | " +
                        rs.getInt("ID_Empleado") + " | " +
                        rs.getInt("ID_Proveedor") + " | " +
                        rs.getString("Fecha_Ingreso") + " | " +
                        rs.getDouble("Total");
            }
        });
    }

    public boolean agregarIngreso(IngresoCompra ingresoCompra) {
        // Validar existencia de ID_Empleado
        String checkEmpleado = "SELECT COUNT(*) FROM empleado WHERE ID_Empleado = ?";
        Integer empleadoCount = jdbcTemplate.queryForObject(checkEmpleado, Integer.class, ingresoCompra.getID_Empleado());
        if (empleadoCount == null || empleadoCount == 0) {
            System.out.println("Error: El empleado con ID " + ingresoCompra.getID_Empleado() + " no existe.");
            return false;
        }

        // Validar existencia de ID_Proveedor
        String checkProveedor = "SELECT COUNT(*) FROM proveedor WHERE ID_Proveedor = ?";
        Integer proveedorCount = jdbcTemplate.queryForObject(checkProveedor, Integer.class, ingresoCompra.getID_Proveedor());
        if (proveedorCount == null || proveedorCount == 0) {
            System.out.println("Error: El proveedor con ID " + ingresoCompra.getID_Proveedor() + " no existe.");
            return false;
        }

        String sql = "INSERT INTO ingreso_compra (ID_Empleado, ID_Proveedor, Total) VALUES (?, ?, ?)";

        int rows = jdbcTemplate.update(sql,
                ingresoCompra.getID_Empleado(),
                ingresoCompra.getID_Proveedor(),
                ingresoCompra.getTotal()
        );

        return rows > 0;
    }


    public boolean actualizarIngreso(int id, IngresoCompra ingresoCompra) {
        // Validar existencia de ID_Empleado
        String checkEmpleado = "SELECT COUNT(*) FROM empleado WHERE ID_Empleado = ?";
        Integer empleadoCount = jdbcTemplate.queryForObject(checkEmpleado, Integer.class, ingresoCompra.getID_Empleado());
        if (empleadoCount == null || empleadoCount == 0) {
            System.out.println("Error: El empleado con ID " + ingresoCompra.getID_Empleado() + " no existe.");
            return false;
        }

        // Validar existencia de ID_Proveedor
        String checkProveedor = "SELECT COUNT(*) FROM proveedor WHERE ID_Proveedor = ?";
        Integer proveedorCount = jdbcTemplate.queryForObject(checkProveedor, Integer.class, ingresoCompra.getID_Proveedor());
        if (proveedorCount == null || proveedorCount == 0) {
            System.out.println("Error: El proveedor con ID " + ingresoCompra.getID_Proveedor() + " no existe.");
            return false;
        }

        
        String sql = "UPDATE ingreso_compra " +
                "SET ID_Empleado = ?, ID_Proveedor = ?, Total = ? " +
                "WHERE ID_Ingreso = ?";

        int rows = jdbcTemplate.update(sql,
                ingresoCompra.getID_Empleado(),
                ingresoCompra.getID_Proveedor(),
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

