package com.example.demo.java1.Ventas.Pedido;
import com.example.demo.java1.Ventas.DetallePedido.DetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Listar pedidos por cliente
    public List<Pedido> listarPorCliente(int idCliente) {

        String sql = """
        SELECT 
            p.ID_Pedido,
            p.ID_Cliente,
            p.fecha,
            p.total,
            p.estado AS estado_pedido,
            pa.estado AS estado_pago,
            e.estado AS estado_envio
        FROM pedido p
        LEFT JOIN pago pa ON p.ID_Pedido = pa.ID_Pedido
        LEFT JOIN envio e ON p.ID_Pedido = e.ID_Pedido
        WHERE p.ID_Cliente = ?
        ORDER BY p.fecha DESC
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("ID_Pedido"));
            pedido.setIdCliente(rs.getInt("ID_Cliente"));
            pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            pedido.setTotal(rs.getDouble("total"));
            pedido.setEstado(rs.getString("estado_pedido"));
            pedido.setEstadoPago(rs.getString("estado_pago"));
            pedido.setEstadoEnvio(rs.getString("estado_envio"));
            return pedido;
        }, idCliente);
    }


    // Obtener pedido por ID
    public Pedido obtenerPorId(int idPedido) {

        String sql = """
        SELECT 
            p.ID_Pedido,
            p.ID_Cliente,
            p.fecha,
            p.total,
            p.estado AS estado_pedido,
            pa.metodo,
            pa.estado AS estado_pago,
            e.direccion,
            e.ciudad,
            e.estado AS estado_envio
        FROM pedido p
        LEFT JOIN pago pa ON p.ID_Pedido = pa.ID_Pedido
        LEFT JOIN envio e ON p.ID_Pedido = e.ID_Pedido
        WHERE p.ID_Pedido = ?
    """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("ID_Pedido"));
            pedido.setIdCliente(rs.getInt("ID_Cliente"));
            pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            pedido.setTotal(rs.getDouble("total"));
            pedido.setEstado(rs.getString("estado_pedido"));

            pedido.setMetodoPago(rs.getString("metodo"));
            pedido.setEstadoPago(rs.getString("estado_pago"));

            pedido.setDireccion(rs.getString("direccion"));
            pedido.setCiudad(rs.getString("ciudad"));
            pedido.setEstadoEnvio(rs.getString("estado_envio"));

            return pedido;
        }, idPedido);
    }



    // Cambiar estado
    public void cambiarEstado(int idPedido, String estado) {
        jdbcTemplate.update(
                "UPDATE pedido SET estado = ? WHERE ID_Pedido = ?",
                estado,
                idPedido
        );
    }
    // Obtener productos del pedido
    public List<DetallePedido> obtenerDetallePedido(int idPedido) {

        String sql = """
        SELECT 
            dp.ID_Producto,
            p.nombre,
            dp.cantidad,
            dp.precio_unitario,
            dp.total
        FROM detalle_pedido dp
        JOIN producto p ON dp.ID_Producto = p.ID_Producto
        WHERE dp.ID_Pedido = ?
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            DetallePedido d = new DetallePedido();
            d.setIdProducto(rs.getInt("ID_Producto"));
            d.setNombre(rs.getString("nombre"));
            d.setCantidad(rs.getInt("cantidad"));
            d.setPrecioUnitario(rs.getDouble("precio_unitario"));
            d.setTotal(rs.getDouble("total"));
            return d;
        }, idPedido);
    }

}
