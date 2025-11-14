package com.example.demo.java1.Productos.producto;

import org.springframework.web.multipart.MultipartFile;

public class Producto {

    private int idProducto; // este se genera automáticamente (AUTO_INCREMENT)
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int idProveedor;
    private String imagen;
    private String estado;

    // Constructor vacío (obligatorio para Spring)
    public Producto() {}

    // Constructor para crear nuevos productos (sin idProducto)
    public Producto(String nombre, String descripcion, double precio, int stock, int idProveedor, MultipartFile imagen, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idProveedor = idProveedor;
        this.imagen = String.valueOf(imagen);
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getID_Proveedor() { return idProveedor; }
    public void setID_Proveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }


}
