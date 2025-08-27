package com.example.demo.men1.ListaDeseos;

import java.time.LocalDate;

public class ListaDeseos {

    private int idLista;
    private int idCliente;
    private LocalDate fechaCreacion; // ✅ camelCase en vez de fecha_creacion

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaCreacion() {   // ✅ cambia a camelCase
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {  // ✅ camelCase
        this.fechaCreacion = fechaCreacion;
    }
}

