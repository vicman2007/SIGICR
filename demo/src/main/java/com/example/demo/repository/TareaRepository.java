package com.example.demo.repository;

import com.example.demo.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByUsuarioId(Long usuarioId);

}