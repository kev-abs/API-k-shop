package com.example.demo.java1.Inventario.Cupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ConexionServiceCupon {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // =============================
    // OBTENER TODOS LOS CUPONES
    // =============================

    public List<Cupon> obtenerCupon() {

        String sql = "SELECT * FROM cupon";

        return jdbcTemplate.query(sql, new RowMapper<Cupon>() {

            @Override
            public Cupon mapRow(ResultSet rs, int rowNum) throws SQLException {

                Cupon c = new Cupon();

                c.setID_Cupon(rs.getInt("ID_Cupon"));
                c.setCodigo(rs.getString("Codigo"));
                c.setDescuento(rs.getInt("Descuento"));
                c.setFecha_Expiracion(rs.getDate("Fecha_Expiracion").toLocalDate());
                c.setEstado(rs.getString("estado"));

                return c;
            }
        });
    }

    // =============================
    // AGREGAR CUPON
    // =============================

    public void agregarCupon(Cupon cupon) {

        if (cupon.getCodigo() == null || cupon.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("El código del cupón no puede estar vacío");
        }

        String sql = "INSERT INTO cupon (Codigo, Descuento, Fecha_Expiracion, estado) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                cupon.getCodigo(),
                cupon.getDescuento(),
                cupon.getFecha_Expiracion(),
                cupon.getEstado()
        );
    }

    // =============================
    // ACTUALIZAR CUPON
    // =============================

    public boolean actualizarCupon(int id, Cupon cupon) {

        String sql = "UPDATE cupon SET Codigo = ?, Descuento = ?, Fecha_Expiracion = ?, estado = ? WHERE ID_Cupon = ?";

        int filasAfectadas = jdbcTemplate.update(sql,
                cupon.getCodigo(),
                cupon.getDescuento(),
                cupon.getFecha_Expiracion(),
                cupon.getEstado(),
                id
        );

        return filasAfectadas > 0;
    }

    // =============================
    // ELIMINAR CUPON
    // =============================

    public boolean eliminarCupon(int id) {

        String sql = "DELETE FROM cupon WHERE ID_Cupon = ?";

        int filasAfectadas = jdbcTemplate.update(sql, id);

        return filasAfectadas > 0;
    }

    // =============================
    // VALIDAR CUPON
    // =============================

    public Cupon validarCupon(String codigo, int idCliente) {

        String sql = "SELECT c.*\n" + "FROM cupon c\n" + "JOIN cupon_cliente cc ON c.ID_Cupon = cc.ID_Cupon\n" + "WHERE c.Codigo = ?\n" + "AND cc.ID_Cliente = ?\n" + "AND cc.Usado = 0\n" + "AND c.estado = '1'\n" + "AND c.Fecha_Expiracion >= CURRENT_DATE";

        List<Cupon> lista = jdbcTemplate.query(sql,
                new Object[]{codigo, idCliente},
                new RowMapper<>() {

                    @Override
                    public Cupon mapRow(ResultSet rs, int rowNum) throws SQLException {

                        Cupon c = new Cupon();

                        c.setID_Cupon(rs.getInt("ID_Cupon"));
                        c.setCodigo(rs.getString("Codigo"));
                        c.setDescuento(rs.getInt("Descuento"));
                        c.setFecha_Expiracion(rs.getDate("Fecha_Expiracion").toLocalDate());
                        c.setEstado(rs.getString("estado"));

                        return c;
                    }
                });

        if (lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }

    // =============================
    // MARCAR CUPON COMO USADO
    // =============================

    public void usarCupon(int idCliente, int idCupon) {

        String sql = "UPDATE cupon_cliente\n" + "SET Usado = 1\n" + "WHERE ID_Cliente = ?\n" + " AND ID_Cupon = ?";

        jdbcTemplate.update(sql, idCliente, idCupon);
    }

    // =============================
    // ASIGNAR CUPON A CLIENTE
    // =============================

    public void asignarCupon(int idCliente, int idCupon){

        String sql = "INSERT INTO cupon_cliente (ID_Cliente, ID_Cupon, Usado) VALUES (?, ?, 0)";

        jdbcTemplate.update(sql, idCliente, idCupon);
    }
}