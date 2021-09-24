package com.example.videojuegos.controllers;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.services.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ControladorVideojuego {

    @Autowired
    private VideojuegoService videojuegoService;


    @GetMapping(value = "/inicio")
    public String inicio(Model model){
        try{
            List<Videojuego> videojuegoList = this.videojuegoService.findAllByActivo();
            model.addAttribute("lista_videojuegos", videojuegoList);
            return "views/inicio";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/detalle/{id}")
    public String detalleVideojuego(Model model, @PathVariable("id") Long id) throws Exception {
        try {
            Videojuego videojuego = this.videojuegoService.findById(id);
            model.addAttribute("videojuego", videojuego);
            return "views/detalle";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
