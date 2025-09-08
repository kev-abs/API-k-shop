package com.example.demo.java1.Ventas.Pedido;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConexionServicePedido {

    private final JdbcTemplate jdbcTemplate;

    public ConexionServicePedido(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pedido> obtenerPedidos() {
        String sql = "SELECT * FROM pedido";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pedido.class));
    }

    public Pedido obtenerPedidoPorId(int id) {
        String sql = "SELECT * FROM pedido WHERE ID_Pedido = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Pedido.class), id);
    }

    public int agregarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedido (ID_Cliente, Fecha_Pedido, Estado, Total) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                pedido.getID_Cliente(),
                pedido.getFecha_Pedido(),
                pedido.getEstado(),
                pedido.getTotal());
    }

    public int actualizarPedido(int id, Pedido pedido) {
        String sql = "UPDATE pedido SET ID_Cliente = ?, Fecha_Pedido = ?, Estado = ?, Total = ? WHERE ID_Pedido = ?";
        return jdbcTemplate.update(sql,
                pedido.getID_Cliente(),
                pedido.getFecha_Pedido(),
                pedido.getEstado(),
                pedido.getTotal(),
                id);
    }

    public int eliminarPedido(int id) {
        String sql = "DELETE FROM pedido WHERE ID_Pedido = ?";
        return jdbcTemplate.update(sql, id);
    }
}