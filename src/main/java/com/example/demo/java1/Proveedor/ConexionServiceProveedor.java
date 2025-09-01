package com.example.demo.java1.Proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ConexionServiceProveedor {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<String> obtenerProveedor() {
        String sql = "SELECT * FROM proveedor";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("ID_Proveedor") + " " +
                        rs.getString("Nombre_Empresa") + " " +
                        rs.getString("Contacto") + " "+
                        rs.getString("Telefono") + " "+
                        rs.getString("Correo") + " "+
                        rs.getString("Direccion");
            }
        });

    }





    public void agregarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedor" +
                "(ID_Proveedor, Nombre_Empresa, Contacto, Telefono, Correo, Direccion)" + "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                proveedor.getID_Proveedor(),
                proveedor.getNombre_Empresa(),
                proveedor.getContacto(),
                proveedor.getTelefono(),
                proveedor.getCorreo(),
                proveedor.getDireccion()
        );
    }






    public boolean actualizarProveedor(int id, Proveedor proveedor) {
        String sql = "UPDATE proveedor SET  Nombre_Empresa = ?, Contacto = ?, Telefono = ?, Correo = ?, Direccion = ?  WHERE ID_Proveedor = ?";
        int filasAfectadas = jdbcTemplate.update(sql,
                proveedor.getNombre_Empresa(),
                proveedor.getContacto(),
                proveedor.getTelefono(),
                proveedor.getCorreo(),
                proveedor.getDireccion(),
                id
        );
        return filasAfectadas > 0;
    }



    public boolean eliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor cupon WHERE ID_Proveedor = ?";
        int filasAfectadas = jdbcTemplate.update(sql, id);
        return filasAfectadas > 0;
    }
}