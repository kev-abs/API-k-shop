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
public class ConexionServiceCupon_Cliente {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> obtenerCupon_Cliente() {
        String sql = "SELECT * FROM cupon_cliente";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("ID_Cliente") + " " +
                        rs.getString("ID_Cupon") + " " +
                        rs.getString("Usado");
            }
        });

    }
    public void agregarCupon_Cliente(Cupon_Cliente cupon_cliente) {
        String sql = "INSERT INTO cupon_cliente" +
                "(ID_Cliente, ID_Cupon, Usado)" + "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                cupon_cliente.getID_Cliente(),
                cupon_cliente.getID_Cupon(),
                cupon_cliente.getUsado()
        );
    }


}
