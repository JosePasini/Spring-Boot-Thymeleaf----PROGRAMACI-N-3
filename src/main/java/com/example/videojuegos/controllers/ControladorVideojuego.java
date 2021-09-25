package com.example.videojuegos.controllers;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.services.CategoriaService;
import com.example.videojuegos.services.EstudioService;
import com.example.videojuegos.services.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControladorVideojuego {

    @Autowired
    private VideojuegoService videojuegoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private EstudioService estudioService;

    @GetMapping(value = "/inicio")
    public String inicio(Model model){
        try{
            List<Videojuego> videojuegoList = this.videojuegoService.findAllByActivo();
            model.addAttribute("videojuegos", videojuegoList);
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
            model.addAttribute("videojuegos", videojuego);
            return "views/detalle";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/busqueda")
    public String busquedaVideojuego(Model model, @RequestParam(value = "query", required = false) String q){
        try{
            List<Videojuego> videojuegoList = this.videojuegoService.findByTitulo(q);
            model.addAttribute("videojuegos", videojuegoList);
            return "views/busqueda";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/crud")
    public String crudVideojuego(Model model){
        try{
            List<Videojuego> videojuegoList = this.videojuegoService.findAll();
            model.addAttribute("videojuegos", videojuegoList);
            return "views/crud";
        } catch (Exception e){
            model.addAttribute("error", "ERROR ERROR ERROR");
            return "error";
        }
    }

    @GetMapping("/formulario/videojuego/{id}")
    public String formularioVideojuego(Model model, @PathVariable("id") Long id){
        try{

            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("estudios", this.estudioService.findAll());

            if (id == 0){
                model.addAttribute("videojuegos", new Videojuego());
            } else {
                model.addAttribute("videojuegos", this.videojuegoService.findById(id));
            }
            return "views/formulario/videojuego";
        } catch (Exception e){
            model.addAttribute("error", "ERROR ERROR ERROR");
            return "error";
        }
    }

    @PostMapping("/formulario/videojuego/{id}")
    public String guardarVideojuego(@ModelAttribute("videojuegos") Videojuego videojuego, Model model, @PathVariable("id") Long id){
        try{
            if (id == 0){
                this.videojuegoService.saveOne(videojuego);
            } else {

                this.videojuegoService.updateOne(videojuego,id);
            }
            return "redirect:/crud";
        } catch (Exception e){
            model.addAttribute("error", "ERROR ERROR ERROR");
            return "error";
        }
    }



}
