package com.example.demo.java1.Usuario.empleado;

import java.math.BigInteger;

public class EmpleadoDTO {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(BigInteger idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    private String estado; // tinyint(1) → boolean

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    private BigInteger IdEmpleado;
    private String nombre;
    private String cargo;
    private String correo;
    private String contrasena;
    private String fechaContratacion;
    // yyyy-MM-dd HH:mm:ss
    private String documento;
    private String telefono;

    }