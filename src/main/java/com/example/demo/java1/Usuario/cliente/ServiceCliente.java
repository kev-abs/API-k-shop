package com.example.demo.java1.Usuario.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceCliente {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obtener lista de clientes
    public List<String> obtenerClientes() {
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("ID_Cliente") + " | " +
                        rs.getString("Nombre") + " | " +
                        rs.getString("Correo") + " | " +
                        rs.getString("Contrasena") + " | " +
                        rs.getString("Fecha_Registro") + " | " +
                        rs.getString("Estado") + " | " +
                        rs.getString("Documento") + " | " +
                        rs.getString("Telefono");
            }
        });
    }

    // Insertar cliente (POST)
    public int insertarCliente(String nombre, String correo, String contrasena,
                               String documento, String telefono) {
        String sql = "INSERT INTO cliente (Nombre, Correo, Contrasena, Documento, Telefono) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, nombre, correo, contrasena, documento, telefono);
    }

    // Actualizar cliente (PUT)
    public int actualizarCliente(int id, String nombre, String correo, String contrasena,
                                 LocalDate fechaRegistro, String estado,
                                 String documento, String telefono) {
        String sql = "UPDATE cliente SET Nombre=?, Correo=?, Contrasena=?, Fecha_Registro=?, Estado=?, Documento=?, Telefono=? " +
                "WHERE ID_Cliente=?";
        return jdbcTemplate.update(sql, nombre, correo, contrasena, fechaRegistro, estado, documento, telefono, id);
    }

    // Eliminar cliente (DELETE)
    public int eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE ID_Cliente = ?";
        return jdbcTemplate.update(sql, id);
    }
}
