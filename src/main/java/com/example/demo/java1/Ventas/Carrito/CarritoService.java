package com.example.demo.java1.Ventas.Carrito;

import com.example.demo.java1.Ventas.Carrito.dto.CarritoResponse;
import com.example.demo.java1.Ventas.DetalleCarrito.DetalleCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.java1.Ventas.Exceptions.StockException;
import java.util.List;

@Service
public class CarritoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Buscar carrito activo
    public Integer obtenerCarritoActivo(int idCliente) {

        String sql = """
            SELECT ID_Carrito
            FROM carrito
            WHERE ID_Cliente = ? AND activo = 1
        """;

        List<Integer> ids = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getInt("ID_Carrito"),
                idCliente
        );

        return ids.isEmpty() ? null : ids.get(0);
    }

    // Crear carrito si no existe
    public int crearCarrito(int idCliente) {

        jdbcTemplate.update(
                "INSERT INTO carrito (ID_Cliente, activo) VALUES (?, 1)",
                idCliente
        );

        return jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()",
                Integer.class
        );
    }

    // Agregar producto al carrito
    public void agregarProducto(int idCliente, int idProducto, int cantidad) {

        Integer idCarrito = obtenerCarritoActivo(idCliente);

        if (idCarrito == null) {
            idCarrito = crearCarrito(idCliente);
        }
// ======================
// VALIDAR STOCK
// ======================

        Integer stockActual = jdbcTemplate.queryForObject(
                "SELECT stock FROM producto WHERE ID_Producto = ?",
                Integer.class,
                idProducto
        );

        Integer cantidadActual = jdbcTemplate.queryForObject(
                """
                SELECT IFNULL(SUM(cantidad),0)
                FROM detalle_carrito dc
                JOIN carrito c ON dc.ID_Carrito = c.ID_Carrito
                WHERE c.ID_Cliente = ? AND c.activo = 1 AND dc.ID_Producto = ?
                """,
                Integer.class,
                idCliente,
                idProducto
        );

        int totalSolicitado = cantidadActual + cantidad;

        if (stockActual == null || totalSolicitado > stockActual) {
            throw new RuntimeException(
                    "Solo quedan " + stockActual + " unidades disponibles"
            );
        }

        String sql = """
            INSERT INTO detalle_carrito (ID_Carrito, ID_Producto, cantidad)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE cantidad = cantidad + ?
        """;

        jdbcTemplate.update(
                sql,
                idCarrito,
                idProducto,
                cantidad,
                cantidad
        );
    }

    // Obtener productos del carrito
    public List<DetalleCarrito> obtenerCarrito(int idCliente) {

        Integer idCarrito = obtenerCarritoActivo(idCliente);

        if (idCarrito == null) {
            return List.of();
        }

        String sql = """
        SELECT 
            dc.ID_Detalle,
            dc.ID_Carrito,
            dc.ID_Producto,
            p.nombre,
            p.precio,
            dc.cantidad,
            (dc.cantidad * p.precio) AS total
        FROM detalle_carrito dc
        JOIN producto p ON dc.ID_Producto = p.ID_Producto
        WHERE dc.ID_Carrito = ?
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            DetalleCarrito d = new DetalleCarrito();
            d.setIdDetalle(rs.getInt("ID_Detalle"));
            d.setIdCarrito(rs.getInt("ID_Carrito"));
            d.setIdProducto(rs.getInt("ID_Producto"));
            d.setNombre(rs.getString("nombre"));
            d.setPrecio(rs.getDouble("precio"));
            d.setCantidad(rs.getInt("cantidad"));
            d.setTotal(rs.getDouble("total"));
            return d;
        }, idCarrito);
    }


    // Vaciar carrito
    public void vaciarCarrito(int idCliente) {

        Integer idCarrito = obtenerCarritoActivo(idCliente);

        if (idCarrito != null) {
            jdbcTemplate.update(
                    "DELETE FROM detalle_carrito WHERE ID_Carrito = ?",
                    idCarrito
            );
        }
    }

    // Cerrar carrito (para checkout después)
    public void cerrarCarrito(int idCliente) {

        Integer idCarrito = obtenerCarritoActivo(idCliente);

        if (idCarrito != null) {
            jdbcTemplate.update(
                    "UPDATE carrito SET activo = 0 WHERE ID_Carrito = ?",
                    idCarrito
            );
        }
    }
    public void actualizarCantidad(int idCliente, int idProducto, int cantidad) {

        Integer idCarrito = obtenerCarritoActivo(idCliente);

        if (idCarrito == null) {
            return; // no hay carrito, no hace nada
        }

        if (cantidad <= 0) {
            // eliminar producto
            jdbcTemplate.update(
                    "DELETE FROM detalle_carrito WHERE ID_Carrito = ? AND ID_Producto = ?",
                    idCarrito,
                    idProducto
            );
        } else {

            jdbcTemplate.update(
                    """
                    UPDATE detalle_carrito 
                    SET cantidad = ?
                    WHERE ID_Carrito = ? AND ID_Producto = ?
                    """,
                    cantidad,
                    idCarrito,
                    idProducto
            );
        }
    }

    public void eliminarProducto(int idCliente, int idProducto) {

        Integer idCarrito = obtenerCarritoActivo(idCliente);
        if (idCarrito == null) {
            return;
        }

        String sql = """
        DELETE FROM detalle_carrito
        WHERE ID_Carrito = ? AND ID_Producto = ?
    """;

        jdbcTemplate.update(sql, idCarrito, idProducto);
    }

    public double calcularSubtotal(int idCarrito) {

        String sql = """
        SELECT IFNULL(SUM(dc.cantidad * p.precio), 0)
        FROM detalle_carrito dc
        JOIN producto p ON dc.ID_Producto = p.ID_Producto
        WHERE dc.ID_Carrito = ?
    """;

        return jdbcTemplate.queryForObject(sql, Double.class, idCarrito);
    }

    public CarritoResponse obtenerCarritoCompleto(int idCliente) {

        Integer idCarrito = obtenerCarritoActivo(idCliente);
        if (idCarrito == null) {
            return new CarritoResponse(List.of(), 0);
        }

        List<DetalleCarrito> items = obtenerCarrito(idCliente);
        double subtotal = calcularSubtotal(idCarrito);

        return new CarritoResponse(items, subtotal);
    }
@Transactional
    public void checkout(int idCliente, String direccion, String ciudad, String metodoPago) {

        Integer idCarrito = obtenerCarritoActivo(idCliente);

        if (idCarrito == null) {
            throw new RuntimeException("No hay carrito activo");
        }

        double total = calcularSubtotal(idCarrito);
// Obtener productos del carrito
    List<DetalleCarrito> items = obtenerCarrito(idCliente);

    for (DetalleCarrito item : items) {

        Integer stockActual = jdbcTemplate.queryForObject(
                "SELECT stock FROM producto WHERE ID_Producto = ? FOR UPDATE",
                Integer.class,
                item.getIdProducto()
        );

        if (stockActual == null || stockActual <= 0) {

            throw new RuntimeException(
                    "El producto " + item.getIdProducto() + " ya no tiene stock"
            );
        }

        if (item.getCantidad() > stockActual) {

            throw new StockException(
                    "Solo quedan " + stockActual + " unidades disponibles"
            );
        }

            // DESCONTAR STOCK
        jdbcTemplate.update(
                "UPDATE producto SET stock = stock - ? WHERE ID_Producto = ?",
                item.getCantidad(),
                item.getIdProducto()
        );
    }


    // Crear pedido
        jdbcTemplate.update(
                "INSERT INTO pedido (ID_Cliente, total, estado) VALUES (?, ?, 'PENDIENTE')",
                idCliente,
                total
        );

        Integer idPedido = jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()",
                Integer.class
        );

        // Copiar productos
        jdbcTemplate.update("""
        INSERT INTO detalle_pedido (ID_Pedido, ID_Producto, cantidad, precio_unitario, total)
        SELECT ?, dc.ID_Producto, dc.cantidad, p.precio,
               (dc.cantidad * p.precio)
        FROM detalle_carrito dc
        JOIN producto p ON dc.ID_Producto = p.ID_Producto
        WHERE dc.ID_Carrito = ?
    """, idPedido, idCarrito);

        // Crear envío con dirección real
        jdbcTemplate.update("""
        INSERT INTO envio (ID_Pedido, direccion, ciudad, estado)
        VALUES (?, ?, ?, 'PENDIENTE')
    """, idPedido, direccion, ciudad);

        // Crear pago con método real
        jdbcTemplate.update("""
        INSERT INTO pago (ID_Pedido, metodo, monto, estado)
        VALUES (?, ?, ?, 'PENDIENTE')
    """, idPedido, metodoPago, total);

        // Cerrar carrito
        jdbcTemplate.update(
                "UPDATE carrito SET activo = 0 WHERE ID_Carrito = ?",
                idCarrito
        );
    }


}

