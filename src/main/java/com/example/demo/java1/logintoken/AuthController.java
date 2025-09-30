package com.example.demo.java1.logintoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Aquí deberías validar el usuario en BD
        if ("admin".equals(username) && "admin123".equals(password)) {
            return jwtUtil.generarToken(username);
        }
        return "Usuario o contraseña incorrectos";
    }
}
