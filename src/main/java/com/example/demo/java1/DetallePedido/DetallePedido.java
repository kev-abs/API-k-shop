package com.example.demo.java1.DetallePedido;

public class DetallePedido {
    private int ID_Detalle;
    private int ID_Pedido;
    private int ID_Producto;
    private int Cantidad;
    private double Precio_Unitario;

    // Getters y setters
    public int getID_Detalle() { return ID_Detalle; }
    public void setID_Detalle(int ID_Detalle) { this.ID_Detalle = ID_Detalle; }

    public int getID_Pedido() { return ID_Pedido; }
    public void setID_Pedido(int ID_Pedido) { this.ID_Pedido = ID_Pedido; }

    public int getID_Producto() { return ID_Producto; }
    public void setID_Producto(int ID_Producto) { this.ID_Producto = ID_Producto; }

    public int getCantidad() { return Cantidad; }
    public void setCantidad(int cantidad) { Cantidad = cantidad; }

    public double getPrecio_Unitario() { return Precio_Unitario; }
    public void setPrecio_Unitario(double precio_Unitario) { Precio_Unitario = precio_Unitario; }
}
