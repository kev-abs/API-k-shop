package com.example.demo.java1.Usuario.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ServiceEmpleado {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // Obtener lista de Usuarios
    public List<String> obtenerEmpleados() {
        String sql = "SELECT * FROM Empleado";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return
                        rs.getInt("ID_Empleado") + " | " +
                                rs.getString("Nombre") + " | " +
                                rs.getString("Cargo")+" | "+
                                rs.getString("Correo")+" | "+
                                rs.getString("Contrasena")+" | "+
                                rs.getString("Fecha_Contratacion")+" | "+
                                rs.getString("Estado")+" ";
            }
        });
    }
    // Post empleados
    public int insertarEmpleado(String nombre, String cargo, String correo, String contrasena) {
        String sql = "INSERT INTO empleado (Nombre, Cargo, Correo, Contrasena) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, nombre, cargo, correo, contrasena);
    }
    // Put
    public int actualizarEmpleado(int id, String nombre, String cargo, String correo, String contrasena, String fechaContratacion,
                                  String estado) {
        String sql = "UPDATE empleado SET Nombre=?, Cargo=?, Correo=?, Contrasena=?, Estado=? WHERE ID_Empleado=?";
        return jdbcTemplate.update(sql, nombre, cargo, correo, contrasena, fechaContratacion, estado, id);
    }
    // Delete
    public int eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleado WHERE ID_Empleado = ?";
        return jdbcTemplate.update(sql, id);
    }
}
