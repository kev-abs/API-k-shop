package com.example.demo.java1.Inventario.Cupon;

import java.time.LocalDate;

public class Cupon {
    private int ID_Cupon;
    private String Codigo;
    private int Descuento;
    private LocalDate Fecha_Expiracion;

    public int getID_Cupon(){return ID_Cupon;}
    public void setID_Cupon(int ID_Cupon){this.ID_Cupon=ID_Cupon;}
    public String getCodigo(){return Codigo;}
    public void setCodigo(String codigo){Codigo=codigo;}
    public int getDescuento(){return Descuento;}
    public void setDescuento(int descuento){Descuento=descuento;}
    public LocalDate getFecha_Expiracion(){return Fecha_Expiracion;}
    public void setFecha_Expiracion(LocalDate fecha_Expiracion){Fecha_Expiracion=fecha_Expiracion;}
}
