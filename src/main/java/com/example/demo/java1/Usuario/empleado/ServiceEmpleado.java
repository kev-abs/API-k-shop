package com.example.demo.java1.Usuario.empleado;

import com.example.demo.java1.utils.PasswordUtils;
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

    public List<EmpleadoDTO> obtenerEmpleados() {
        String sql = "SELECT * FROM Empleado";
        return jdbcTemplate.query(sql, new RowMapper<EmpleadoDTO>() {
            @Override
            public EmpleadoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                EmpleadoDTO empleado = new EmpleadoDTO();
                empleado.setNombre(rs.getString("Nombre"));
                empleado.setCargo(rs.getString("Cargo"));
                empleado.setCorreo(rs.getString("Correo"));
                empleado.setContrasena(rs.getString("Contrasena"));
                empleado.setFechaContratacion(rs.getString("Fecha_Contratacion"));
                empleado.setEstado(rs.getString("Estado"));
                return empleado;
            }
        });
    }
    public int insertarEmpleado(String nombre, String cargo, String correo, String contrasena) {
        String contrasenaEncriptada = PasswordUtils.encriptar(contrasena);
        String sql = "INSERT INTO empleado (Nombre, Cargo, Correo, Contrasena) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, nombre, cargo, correo, contrasenaEncriptada);
    }
    public int actualizarEmpleado(int id, String nombre, String cargo, String correo, String contrasena, String fechaContratacion,
                                  String estado) {
        String contrasenaEncriptada = PasswordUtils.encriptar(contrasena);
        String sql = "UPDATE empleado SET Nombre=?, Cargo=?, Correo=?, Contrasena=?, Estado=? WHERE ID_Empleado=?";
        return jdbcTemplate.update(sql, nombre, cargo, correo, contrasenaEncriptada, estado, id);
    }
    public int eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleado WHERE ID_Empleado = ?";
        return jdbcTemplate.update(sql, id);
    }
}
