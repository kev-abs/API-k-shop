package com.example.demo.java1.Cupon_Cliente;

import com.example.demo.java1.Cupon.Cupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class    ConexionServiceCupon_Cliente {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> obtenerCupon_Cliente() {
        String sql = "SELECT * FROM cupon_cliente";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                rs.getString("ID_Cliente") + " " +
                        rs.getString("ID_Cupon") + " " +
                        rs.getString("Usado")
        );
    }

    public String agregarCupon_Cliente(Cupon_Cliente cupon_cliente) {
        // Verificar si el cliente existe
        String checkCliente = "SELECT COUNT(*) FROM cliente WHERE ID_Cliente = ?";
        Integer clienteCount = jdbcTemplate.queryForObject(checkCliente, Integer.class, cupon_cliente.getID_Cliente());
        if (clienteCount == null || clienteCount == 0) {
            return "Error: El cliente con ID " + cupon_cliente.getID_Cliente() + " no existe.";
        }

        // Verificar si el cupón existe
        String checkCupon = "SELECT COUNT(*) FROM cupon WHERE ID_Cupon = ?";
        Integer cuponCount = jdbcTemplate.queryForObject(checkCupon, Integer.class, cupon_cliente.getID_Cupon());
        if (cuponCount == null || cuponCount == 0) {
            return "Error: El cupón con ID " + cupon_cliente.getID_Cupon() + " no existe.";
        }
        
        String sql = "INSERT INTO cupon_cliente (ID_Cliente, ID_Cupon, Usado) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                cupon_cliente.getID_Cliente(),
                cupon_cliente.getID_Cupon(),
                cupon_cliente.getUsado()
        );



        return "Cupon agregado correctamente.";
    }
    public String actualizarCupon_Cliente(int idClienteAntiguo, int idCuponAntiguo, Cupon_Cliente cupon_cliente) {

        String checkCliente = "SELECT COUNT(*) FROM cliente WHERE ID_Cliente = ?";
        Integer clienteCount = jdbcTemplate.queryForObject(checkCliente, Integer.class, cupon_cliente.getID_Cliente());
        if (clienteCount == null || clienteCount == 0) {
            return "Error: El cliente con ID " + cupon_cliente.getID_Cliente() + " no existe.";
        }


        String checkCupon = "SELECT COUNT(*) FROM cupon WHERE ID_Cupon = ?";
        Integer cuponCount = jdbcTemplate.queryForObject(checkCupon, Integer.class, cupon_cliente.getID_Cupon());
        if (cuponCount == null || cuponCount == 0) {
            return "Error: El cupón con ID " + cupon_cliente.getID_Cupon() + " no existe.";
        }

        String sql = "UPDATE cupon_cliente SET ID_Cliente = ?, ID_Cupon = ?, Usado = ? " +
                "WHERE ID_Cliente = ? AND ID_Cupon = ?";
        int filasAfectadas = jdbcTemplate.update(sql,
                cupon_cliente.getID_Cliente(),
                cupon_cliente.getID_Cupon(),
                cupon_cliente.getUsado(),
                idClienteAntiguo,
                idCuponAntiguo
        );

        if (filasAfectadas > 0) {
            return "Cupon_Cliente actualizado correctamente.";
        } else {
            return "Error: No se encontró el registro a actualizar.";
        }
    }

}
