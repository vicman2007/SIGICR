package com.example.demo.controllers;

import com.example.demo.models.Tarea;
import com.example.demo.service.TareaService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public List<Tarea> listar() {
        return tareaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(
            @RequestBody Tarea tarea) {

        Tarea nueva = tareaService.guardar(tarea);

        return ResponseEntity.ok(nueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerPorId(
            @PathVariable Long id) {

        Tarea tarea = tareaService.obtenerPorId(id);

        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tarea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        tareaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}