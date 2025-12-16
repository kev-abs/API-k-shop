package com.example.demo.java1.Ventas.Checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
