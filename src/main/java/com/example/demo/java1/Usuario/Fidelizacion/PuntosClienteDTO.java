package com.example.demo.java1.Usuario.Fidelizacion;

import java.time.LocalDateTime;

public class PuntosClienteDTO {

    private Integer idPuntos;
    private Integer idCliente;
    private Integer puntos;
    private LocalDateTime fechaActualizacion;

    // Constructor vacío
    public PuntosClienteDTO() {}

    // Constructor con parámetros
    public PuntosClienteDTO(Integer idPuntos, Integer idCliente, Integer puntos, LocalDateTime fechaActualizacion) {
        this.idPuntos = idPuntos;
        this.idCliente = idCliente;
        this.puntos = puntos;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y Setters
    public Integer getIdPuntos() {
        return idPuntos;
    }

    public void setIdPuntos(Integer idPuntos) {
        this.idPuntos = idPuntos;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
