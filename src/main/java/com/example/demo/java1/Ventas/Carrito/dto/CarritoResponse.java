package com.example.demo.java1.Ventas.Carrito.dto;

import com.example.demo.java1.Ventas.DetalleCarrito.DetalleCarrito;

import java.util.List;

public class CarritoResponse {

    private List<DetalleCarrito> items;
    private double subtotal;

    public CarritoResponse(List<DetalleCarrito> items, double subtotal) {
        this.items = items;
        this.subtotal = subtotal;
    }

    public List<DetalleCarrito> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }
}

