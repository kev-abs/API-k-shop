package com.example.demo.java1.Ventas.Pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Consultar pago por pedido
    public Pago obtenerPorPedido(int idPedido) {

        String sql = "SELECT * FROM pago WHERE ID_Pedido = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Pago p = new Pago();
            p.setIdPago(rs.getInt("ID_Pago"));
            p.setIdPedido(rs.getInt("ID_Pedido"));
            p.setMetodo(rs.getString("metodo"));
            p.setMonto(rs.getDouble("monto"));
            p.setEstado(rs.getString("estado"));
            p.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            return p;
        }, idPedido);
    }


    public void cambiarEstado(int idPago, String estado) {

        jdbcTemplate.update(
                "UPDATE pago SET estado = ? WHERE ID_Pago = ?",
                estado,
                idPago
        );


        if (estado.equals("APROBADO")) {
            jdbcTemplate.update(
                    """
                    UPDATE pedido p
                    JOIN pago pg ON p.ID_Pedido = pg.ID_Pedido
                    SET p.estado = 'PAGADO'
                    WHERE pg.ID_Pago = ?
                    """,
                    idPago
            );
        }
    }
}
