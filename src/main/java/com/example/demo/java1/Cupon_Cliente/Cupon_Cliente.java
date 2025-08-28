package com.example.demo.java1.Cupon_Cliente;

import java.time.LocalDate;

public class Cupon_Cliente {
    private int ID_Cliente;
    private int ID_Cupon;
    private boolean Usado;

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public int getID_Cupon() {
        return ID_Cupon;
    }

    public void setID_Cupon(int ID_Cupon) {
        this.ID_Cupon = ID_Cupon;
    }

    public boolean getUsado() {
        return Usado;
    }

    public void setUsado(boolean usado) {
        Usado = usado;
    }
}
