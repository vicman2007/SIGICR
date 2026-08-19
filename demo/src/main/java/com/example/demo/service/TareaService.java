package com.example.demo.service;

import com.example.demo.models.Tarea;
import com.example.demo.repository.TareaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea obtenerPorId(Long id) {
        return tareaRepository
                .findById(id)
                .orElse(null);
    }

    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}