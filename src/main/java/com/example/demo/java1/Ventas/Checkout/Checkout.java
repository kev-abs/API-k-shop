package com.example.demo.java1.Ventas.Checkout;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;


public class Checkout {




        private int ID_Checkout;

        @JsonProperty("idCliente")
        private int ID_Cliente;

        @JsonProperty("direccion")
        private String Direccion;

        @JsonProperty("metodoPago")
        private String Metodo_Pago;

        @JsonProperty("tipoEntrega")
        private String Tipo_Entrega;

        private Date Fecha_Checkout;


    public int getID_Checkout() {
        return ID_Checkout;
    }

    public void setID_Checkout(int ID_Checkout) {
        this.ID_Checkout = ID_Checkout;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getMetodo_Pago() {
        return Metodo_Pago;
    }

    public void setMetodo_Pago(String Metodo_Pago) {
        this.Metodo_Pago = Metodo_Pago;
    }

    public String getTipo_Entrega() {
        return Tipo_Entrega;
    }

    public void setTipo_Entrega(String Tipo_Entrega) {
        this.Tipo_Entrega = Tipo_Entrega;
    }

    public Date getFecha_Checkout() {
        return Fecha_Checkout;
    }

    public void setFecha_Checkout(Date Fecha_Checkout) {
        this.Fecha_Checkout = Fecha_Checkout;
    }
}
