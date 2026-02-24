package com.example.demo.java1.Inventario.IngresoCompra;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class IngresoCompra {

    private int ID_Ingreso;

    @JsonProperty("ID_Empleado")
    private int idEmpleado;

    @JsonProperty("ID_Proveedor")
    private int idProveedor;

    @JsonProperty("Fecha_Ingreso")
    private String fechaIngreso;

    @JsonProperty("Total")
    private String total;

    // getters y setters

    public int getID_Ingreso() {
        return ID_Ingreso;
    }

    public void setID_Ingreso(int ID_Ingreso) {
        this.ID_Ingreso = ID_Ingreso;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
