package com.example.demo.java1.Ventas.Checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List; // 🔥 FALTABA ESTE IMPORT

@Service
public class CheckoutService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Checkout> obtenerPorCliente(int idCliente) {

        String sql = """
            SELECT 
                ID_Checkout,
                ID_Cliente,
                Direccion,
                Metodo_Pago,
                Tipo_Entrega,
                Fecha_Checkout
            FROM checkout
            WHERE ID_Cliente = ?
            ORDER BY Fecha_Checkout ASC
        """;

        return jdbcTemplate.query(sql, new Object[]{idCliente}, (rs, rowNum) -> {
            Checkout c = new Checkout();
            c.setID_Checkout(rs.getInt("ID_Checkout"));
            c.setID_Cliente(rs.getInt("ID_Cliente"));
            c.setDireccion(rs.getString("Direccion"));
            c.setMetodo_Pago(rs.getString("Metodo_Pago"));
            c.setTipo_Entrega(rs.getString("Tipo_Entrega"));
            c.setFecha_Checkout(rs.getTimestamp("Fecha_Checkout"));
            return c;
        });
    }

    // 🔹 REGISTRAR CHECKOUT
    public void registrarCheckout(Checkout checkout) {

        String sql = """
            INSERT INTO checkout
            (ID_Cliente, Direccion, Metodo_Pago, Tipo_Entrega, Fecha_Checkout)
            VALUES (?, ?, ?, ?, NOW())
        """;

        jdbcTemplate.update(
                sql,
                checkout.getID_Cliente(),
                checkout.getDireccion(),
                checkout.getMetodo_Pago(),
                checkout.getTipo_Entrega()
        );
    }
}
