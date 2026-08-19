package com.example.demo.controllers;

import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario guardar(
            @RequestBody Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @PathVariable Long id) {

        return usuarioRepository.findById(id)
                .map(usuario -> {

                    usuarioRepository.delete(usuario);

                    return ResponseEntity.ok().build();

                })
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }
}