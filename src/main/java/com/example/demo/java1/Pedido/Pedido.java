package com.example.demo.java1.Pedido;

import java.time.LocalDate;

public class Pedido {

    private int ID_Pedido;
    private int ID_Cliente;
    private LocalDate Fecha_Pedido; // formato: yyyy-MM-dd
    private String Estado;
    private double Total;

    // Getters y Setters
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

    public LocalDate getFecha_Pedido() {
        return Fecha_Pedido;
    }

    public void setFecha_Pedido(LocalDate fecha_Pedido) {
        Fecha_Pedido = fecha_Pedido;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
}
