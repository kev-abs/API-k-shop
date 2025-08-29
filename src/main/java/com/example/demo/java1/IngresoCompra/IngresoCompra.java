package com.example.demo.java1.IngresoCompra;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class IngresoCompra {
    private int ID_Ingreso;
    @JsonProperty("ID_Empleado")
    private int ID_Empleado;
    @JsonProperty("ID_Proveedor")
    private int ID_Proveedor;
    private String Fecha_Ingreso;
    private String Total;

    public int getID_Ingreso_Compra() {
        return ID_Ingreso;
    }

    public void setID_Ingreso(int ID_Ingreso) {
        this.ID_Ingreso = ID_Ingreso;
    }

    public int getID_Empleado() {
        return ID_Empleado;
    }

    public void setID_Empleado(int ID_Empleado) {
        this.ID_Empleado = ID_Empleado;
    }

    public int getID_Proveedor() {
        return ID_Proveedor;
    }

    public void setID_Proveedor(int ID_Proveedor) {
        this.ID_Proveedor = ID_Proveedor;
    }

    public String getFecha_Ingreso() {
        return Fecha_Ingreso;
    }

    public void setFecha_Ingreso(String fecha_Ingreso) {
        Fecha_Ingreso = fecha_Ingreso;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
