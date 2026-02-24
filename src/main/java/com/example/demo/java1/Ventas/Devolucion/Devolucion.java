package com.example.demo.java1.Ventas.Devolucion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Devolucion {
    private int ID_Devolucion;
    @JsonProperty("ID_Pedido")
    private int ID_Pedido;
    @JsonProperty("ID_Producto")
    private int ID_Producto;
    private String Motivo;
    private EstadoDevolucion Estado;

    public int getID_Devolucion() {
        return ID_Devolucion;
    }

    public void setID_Devolucion(int ID_Devolucion) {
        this.ID_Devolucion = ID_Devolucion;
    }

    public int getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(int ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public EstadoDevolucion getEstado() {
        return Estado;
    }

    public void setEstado(EstadoDevolucion estado) {
        Estado = estado;
    }
}


