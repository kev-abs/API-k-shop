package com.example.demo.java1.Usuario.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerEmpleado {

    @Autowired
    private ServiceEmpleado conexionService;

    // Listar empleados
    @GetMapping("/empleados")
    public List<String> listarEmpleados() {
        return conexionService.obtenerEmpleados();
    }
    // Crear empleado
    @PostMapping("/empleados")
    public String crearEmpleado(@RequestBody EmpleadoDTO empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            return "Error: El nombre no puede estar vacío";
        }

        int filas = conexionService.insertarEmpleado(
                empleado.getNombre(),
                empleado.getCargo(),
                empleado.getCorreo(),
                empleado.getContrasena(),
                empleado.getFechaContratacion(),
                empleado.getEstado()
        );

        return (filas > 0) ? "Empleado agregado correctamente" : "Error al agregar empleado";
    }

    // Actualizar empleado
    @PutMapping("/empleados/{id}")
    public String actualizarEmpleado(@PathVariable int id, @RequestBody EmpleadoDTO empleado) {
        int filas = conexionService.actualizarEmpleado(
                id,
                empleado.getNombre(),
                empleado.getCargo(),
                empleado.getCorreo(),
                empleado.getContrasena(),
                empleado.getFechaContratacion(),
                empleado.getEstado()
        );

        return (filas > 0) ? "Empleado actualizado correctamente" : "Empleado no encontrado o sin cambios";
    }

    // Eliminar empleado
    @DeleteMapping("/empleados/{id}")
    public String eliminarEmpleado(@PathVariable int id) {
        int filas = conexionService.eliminarEmpleado(id);
        return (filas > 0)
                ? "Empleado eliminado correctamente"
                : "Empleado no encontrado";
    }
}
