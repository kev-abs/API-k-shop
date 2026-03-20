package com.example.demo.java1.Ventas.Carrito.dto;


public class CheckoutRequest {

    private int idCliente;
    private String direccion;
    private String ciudad;
    private String metodoPago;
    private double descuento = 0;
    private int porcentajeDescuento = 0;


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    public int getPorcentajeDescuento() { return porcentajeDescuento; }
    public void setPorcentajeDescuento(int porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
