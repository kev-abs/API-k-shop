package com.example.demo.java1.logintoken;

public class LoginResponse {
    private boolean success;
    private String mensaje;
    private String rol;
    private String token;

    public LoginResponse(boolean success, String mensaje, String rol, String token) {
        this.success = success;
        this.mensaje = mensaje;
        this.rol = rol;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getRol() {
        return rol;
    }

    public String getToken() {
        return token;
    }
}
