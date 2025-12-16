package com.example.demo.java1.logintoken;

import com.example.demo.java1.Usuario.cliente.ClienteDTO;
import com.example.demo.java1.Usuario.empleado.EmpleadoDTO;
import com.example.demo.java1.utils.PasswordUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Tag(
        name = "Autenticación",
        description = "Endpoints para login y generación de token JWT"
)
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/login")
    @Operation(
            summary = "Login de usuario",
            description = "Valida credenciales de empleado o cliente y devuelve un token JWT"
    )
    public LoginResponse login(@RequestBody LoginRequest request) {

        String correo = request.getCorreo();
        String contrasena = request.getContrasena();
        System.out.println("LOGIN RECIBIDO:");
        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contrasena);


        // Buscar empleado
        String sqlEmpleado = "SELECT * FROM empleado WHERE Correo = ?";
        List<EmpleadoDTO> empleados = jdbcTemplate.query(sqlEmpleado, new Object[]{correo}, (rs, rowNum) -> {
            EmpleadoDTO e = new EmpleadoDTO();
            e.setNombre(rs.getString("Nombre"));
            e.setCargo(rs.getString("Cargo"));
            e.setCorreo(rs.getString("Correo"));
            e.setContrasena(rs.getString("Contrasena"));
            e.setEstado(rs.getString("Estado"));
            return e;
        });

        if (!empleados.isEmpty()) {
            EmpleadoDTO emp = empleados.get(0);
            String contrasenaBD = emp.getContrasena();

            if (PasswordUtils.verificar(contrasena, contrasenaBD)){
                String token = jwtUtil.generarToken(emp.getCorreo());
                return new LoginResponse(true, "Bienvenido " + emp.getNombre(), "empleado", token);
            }
        }

        // Buscar cliente
        String sqlCliente = "SELECT * FROM cliente WHERE Correo = ?";
        List<ClienteDTO> clientes = jdbcTemplate.query(sqlCliente, new Object[]{correo}, (rs, rowNum) -> {
            ClienteDTO c = new ClienteDTO();
            c.setIdCliente(rs.getInt("ID_Cliente"));
            c.setNombre(rs.getString("Nombre"));
            c.setCorreo(rs.getString("Correo"));
            c.setContrasena(rs.getString("Contrasena"));
            c.setEstado(rs.getString("Estado"));
            return c;
        });

        if (!clientes.isEmpty()) {
            ClienteDTO cli = clientes.get(0);
            String contrasenaBD = cli.getContrasena();

            if (PasswordUtils.verificar(contrasena, contrasenaBD) || contrasena.equals(contrasenaBD)) {
                String token = jwtUtil.generarToken(cli.getCorreo());
                return new LoginResponse(true, "Bienvenido " + cli.getNombre(), "cliente", token);
            }
        }

        return new LoginResponse(false, "Credenciales inválidas", null, null);
    }

}
