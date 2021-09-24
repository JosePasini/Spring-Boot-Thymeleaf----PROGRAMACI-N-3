package com.example.videojuegos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping(value = "/")
    public String index(Model model){
        String saludo = "Hola soy un saludo";
        model.addAttribute("saludo_desde_controlador", saludo);
        return "index";
    }

}
