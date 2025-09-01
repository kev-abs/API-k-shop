package com.example.demo.java1.Pedido;
import java.sql.Date;

public class Pedido {
    private int ID_Pedido;
    private int ID_Cliente;
    private Date Fecha_Pedido;
    private String Estado;
    private double Total;

    public int getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(int ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public Date getFecha_Pedido() {
        return Fecha_Pedido;
    }

    public void setFecha_Pedido(Date Fecha_Pedido) {
        this.Fecha_Pedido = Fecha_Pedido;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
}
