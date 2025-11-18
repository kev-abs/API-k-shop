package com.example.demo.java1.Productos.producto;

public class Producto {

    private Integer ID_Producto;
    private String Nombre;
    private String Descripcion;
    private Double Precio;
    private Integer Stock;
    private Integer ID_Proveedor;
    private String Imagen;
    private String Estado;

    public Producto() {}

    // Getters y Setters
    public Integer getID_Producto() { return ID_Producto; }
    public void setID_Producto(Integer id) { this.ID_Producto = id; }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { this.Nombre = nombre; }

    public String getDescripcion() { return Descripcion; }
    public void setDescripcion(String descripcion) { this.Descripcion = descripcion; }

    public Double getPrecio() { return Precio; }
    public void setPrecio(Double precio) { this.Precio = precio; }

    public Integer getStock() { return Stock; }
    public void setStock(Integer stock) { this.Stock = stock; }

    public Integer getID_Proveedor() { return ID_Proveedor; }
    public void setID_Proveedor(Integer idProveedor) { this.ID_Proveedor = idProveedor; }

    public String getImagen() { return Imagen; }
    public void setImagen(String imagen) { this.Imagen = imagen; }

    public String getEstado() { return Estado; }
    public void setEstado(String estado) { this.Estado = estado; }
}
