package com.example.demo.controllers;

import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> credenciales) {

        String email = credenciales.get("email");
        String password = credenciales.get("password");

        Optional<Usuario> usuarioOpt =
                usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()
                && usuarioOpt.get().getPassword().equals(password)) {

            Usuario usuario = usuarioOpt.get();

            Map<String, Object> respuesta = new HashMap<>();

            respuesta.put("id", usuario.getId());
            respuesta.put("nombre", usuario.getNombre());
            respuesta.put("rol", usuario.getRol().getNombre());

            if ("ADMIN".equals(usuario.getRol().getNombre())) {

                respuesta.put(
                        "redirectUrl",
                        "/dashboard-admin.html"
                );

            } else {

                respuesta.put(
                        "redirectUrl",
                        "/dashboard-empleado.html"
                );
            }

            return ResponseEntity.ok(respuesta);
        }

        return ResponseEntity
                .status(401)
                .body("Credenciales incorrectas");
    }
}