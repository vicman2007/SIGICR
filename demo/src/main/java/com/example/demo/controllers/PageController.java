package com.example.demo.controllers;

import com.example.demo.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final TareaService tareaService;

    public PageController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping({"/", "/login"})
    public String login(Model model) {
        model.addAttribute("pageTitle", "Inicio de Sesión - Sistema de Gestión");
        return "login";
    }

    @GetMapping("/dashboard/admin")
    public String dashboardAdmin(Model model) {
        model.addAttribute("pageTitle", "Panel Administrador");
        model.addAttribute("tareas", tareaService.listarTodas());
        return "dashboard-admin";
    }

    @GetMapping("/dashboard/empleado")
    public String dashboardEmpleado(Model model) {
        model.addAttribute("pageTitle", "Panel Empleado");
        return "dashboard-empleado";
    }
}
