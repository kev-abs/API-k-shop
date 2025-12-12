package com.example.demo.java1.Usuario.cliente;

import com.example.demo.java1.utils.PasswordUtils;
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

    public List<ClienteDTO> obtenerClientes() {
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ClienteDTO c = new ClienteDTO();
            c.setIdCliente(rs.getInt("ID_Cliente"));
            c.setNombre(rs.getString("Nombre"));
            c.setCorreo(rs.getString("Correo"));
            c.setContrasena(rs.getString("Contrasena"));
            c.setFechaRegistro(rs.getDate("Fecha_Registro").toLocalDate());
            c.setEstado(rs.getString("Estado"));
            c.setDocumento(rs.getString("Documento"));
            c.setTelefono(rs.getString("Telefono"));
            return c;
        });
    }

    public int insertarCliente(String nombre, String correo, String contrasena,
                               String documento, String telefono) {
        String contrasenaEncriptada = PasswordUtils.encriptar(contrasena);
        String sql = "INSERT INTO cliente (Nombre, Correo, Contrasena, Documento, Telefono) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, nombre, correo, contrasenaEncriptada, documento, telefono);
    }
    public int actualizarCliente(int id, String nombre, String correo, String contrasena, String estado,
                                 String documento, String telefono) {
        String contrasenaEncriptada = PasswordUtils.encriptar(contrasena);
        String sql = "UPDATE cliente SET Nombre=?, Correo=?, Contrasena=?, Estado=?, Documento=?, Telefono=? " +
                "WHERE ID_Cliente=?";
        return jdbcTemplate.update(sql, nombre, correo, contrasenaEncriptada, estado, documento, telefono, id);
    }

    public int eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE ID_Cliente = ?";
        return jdbcTemplate.update(sql, id);
    }
}