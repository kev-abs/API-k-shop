package com.example.demojava;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ConexionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> obtenerCliente() {
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("Nombre") + "   "+
                        rs.getString("Correo");
            }
        });

    }
    public List<String> obtenerProducto() {
        String sql = "SELECT * FROM producto";
        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("Nombre")+
                        rs.getString("Precio");

            }
        });
    }
    public void agregarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (Nombre, Correo, Contrasena, Fecha_Registro, Estado, Documento, Telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getContrasena(),
                cliente.getFecha_Registro(),
                cliente.getEstado(),
                cliente.getDocumento(),
                cliente.getTelefono()
        );
    }

    public boolean actualizarCliente(int id, Cliente cliente) {
        String sql = "UPDATE cliente SET Nombre = ?, Correo = ?, Contrasena = ?, Fecha_Registro = ?, Estado = ?, Documento = ?, Telefono = ? WHERE ID_Cliente = ?";
        int filasAfectadas = jdbcTemplate.update(sql,
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getContrasena(),
                cliente.getFecha_Registro(),
                cliente.getEstado(),
                cliente.getDocumento(),
                cliente.getTelefono(),
                id
        );
        return filasAfectadas > 0;
    }

    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE ID_Cliente = ?";
        int filasAfectadas = jdbcTemplate.update(sql, id);
        return filasAfectadas > 0;
    }




}
