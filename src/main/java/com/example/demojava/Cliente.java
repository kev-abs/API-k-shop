package com.example.demojava;

import java.time.LocalDate;

public class Cliente {
    private int ID_Cliente;
    private String Nombre;
    private String Correo;
    private String Contrasena;
    private LocalDate Fecha_Registro;
    private String Estado;
    private int Documento;
    private String Telefono;


    public int getID_Cliente() {return ID_Cliente;}
    public void setID_Cliente (int ID_Cliente){this.ID_Cliente = ID_Cliente;}

    public String getNombre() {return Nombre;}
    public void setNombre(String nombre) {Nombre = nombre;}

    public String getCorreo() {return Correo;}
    public void setCorreo(String correo) {Correo = correo;}

    public String getContrasena() { return Contrasena; }
    public void setContrasena(String contrasena) { Contrasena = contrasena; }

    public LocalDate getFecha_Registro() {return Fecha_Registro;}
    public void setFecha_Registro(LocalDate fecha_Registro) {Fecha_Registro = fecha_Registro;}

    public String getEstado() { return Estado; }
    public void setEstado(String estado) { Estado = estado; }

    public int getDocumento() { return Documento; }
    public void setDocumento(int documento) { Documento = documento; }

    public String getTelefono() {return Telefono;}
    public void setTelefono(String telefono) {Telefono = telefono;}
}
