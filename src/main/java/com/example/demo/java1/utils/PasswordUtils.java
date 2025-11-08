package com.example.demo.java1.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encriptar(String contrasena) {
        return encoder.encode(contrasena);
    }

    public static boolean verificar(String contrasenaIngresada, String contrasenaEnBD) {
        if (contrasenaEnBD == null) return false;
        return encoder.matches(contrasenaIngresada, contrasenaEnBD);
    }
}
