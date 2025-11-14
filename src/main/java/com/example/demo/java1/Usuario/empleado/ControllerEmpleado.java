package com.example.demo.java1.Usuario.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class ControllerEmpleado {

    @Autowired
    private ServiceEmpleado conexionService;

    // Listar empleados
    @GetMapping("/empleados")
    public List<EmpleadoDTO> listarEmpleados() {
        return conexionService.obtenerEmpleados();
    }
    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public EmpleadoDTO obtenerEmpleado(@PathVariable("id") BigInteger id) {
        EmpleadoDTO empleado = conexionService.obtenerEmpleadoPorId(id);
        if (empleado == null) {
            throw new RuntimeException("Empleado no encontrado con ID: " + id);
        }
        return empleado;
    }

    // Crear empleado
    @PostMapping("/empleados")
    public Map<String, String> crearEmpleado(@RequestBody EmpleadoDTO empleado) {
        Map<String, String> respuesta = new HashMap<>();

        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            respuesta.put("mensaje", "Error: El nombre no puede estar vacío");
            return respuesta;
        }

        int filas = conexionService.insertarEmpleado(
                empleado.getNombre(),
                empleado.getCargo(),
                empleado.getCorreo(),
                empleado.getContrasena()
        );

        respuesta.put("mensaje", (filas > 0) ? "Empleado agregado correctamente" : "Error al agregar empleado");
        return respuesta;
    }


    // Actualizar empleado
    @PutMapping("/empleados/{id}")
    public Map<String, String> actualizarEmpleado(@PathVariable int id, @RequestBody EmpleadoDTO empleado) {
        int filas = conexionService.actualizarEmpleado(id, empleado.getNombre(), empleado.getCargo(), empleado.getCorreo(), empleado.getContrasena(), empleado.getFechaContratacion(), empleado.getEstado());
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", (filas > 0) ? "Empleado actualizado correctamente" : "Empleado no encontrado o sin cambios");
        return respuesta;
    }


    // Eliminar empleado
    @DeleteMapping("/empleados/{id}")
    public Map<String, String> eliminarEmpleado(@PathVariable int id) {
        int filas = conexionService.eliminarEmpleado(id);
        if (filas > 0) {
            return Map.of("message", "Cliente eliminado correctamente");
        } else {
            return Map.of("message", "Cliente no encontrado");
        }
    }
}
