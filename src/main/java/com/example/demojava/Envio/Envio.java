package com.example.demojava.Envio;


public class Envio {
    private int ID_Envio;          // Autoincrementable
    private int ID_Pedido;
    private String direccionEnvio;
    private String fechaEnvio;
    private String metodoEnvio;
    private String estadoEnvio;

    // Getters y Setters
    public int getID_Envio() { return ID_Envio; }
    public void setID_Envio(int ID_Envio) { this.ID_Envio = ID_Envio; }

    public int getID_Pedido() { return ID_Pedido; }
    public void setID_Pedido(int ID_Pedido) { this.ID_Pedido = ID_Pedido; }

    public String getDireccionEnvio() { return direccionEnvio; }
    public void setDireccionEnvio(String direccionEnvio) { this.direccionEnvio = direccionEnvio; }

    public String getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(String fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public String getMetodoEnvio() { return metodoEnvio; }
    public void setMetodoEnvio(String metodoEnvio) { this.metodoEnvio = metodoEnvio; }

    public String getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(String estadoEnvio) { this.estadoEnvio = estadoEnvio; }
}
