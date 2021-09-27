package com.example.videojuegos.controllers;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.services.CategoriaService;
import com.example.videojuegos.services.EstudioService;
import com.example.videojuegos.services.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public String detalleVideojuego(Model model, @PathVariable("id") long id) throws Exception {
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
    public String formularioVideojuego(Model model, @PathVariable("id") long id){
        try{
            System.out.println("Entrando a: /formulario/videojuego/ " + (id)+ ". ");
            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("estudios", this.estudioService.findAll());


            System.out.println("Antes: ********* {"+ (id) +"} *************");
            if (id == 0){
                model.addAttribute("videojuegos", new Videojuego());
                System.out.println("Después: ********* {"+ (id) +"} *************");
                System.out.println("New videojuego: ********* {"+ (new Videojuego().getId()) +"} *************");
            } else {
                model.addAttribute("videojuegos", this.videojuegoService.findById(id));
            }
            System.out.println("Saliendo del método formularioVieojuego.");
            return "views/formulario/videojuego";
        } catch (Exception e){
            model.addAttribute("error", "ERROR ERROR ERROR");
            return "error";
        }
    }

    @PostMapping ("/formulario/videojuego/{id}")
    public String guardarVideojuego(@ModelAttribute("videojuegos") Videojuego videojuego, Model model, @PathVariable("id") long id){
        System.out.println("######## entrando al método que NO funciona. ########");
        System.out.println("######## {ID}: " + (id) + " ########");
        try{
            if (id == 0){
                System.out.println("######## Id == 0  ########  guarda un nuevo videojuego. ########");
                this.videojuegoService.saveOne(videojuego);
            } else if (id != 0){
                System.out.println("######## Id != 0  ########  actualiza un videojuego existente. ########");
                this.videojuegoService.updateOne(videojuego,id);

            }
            System.out.println("######## Sale del método que no funciona. ########");
            return "redirect:/crud";
        } catch (Exception e){
            model.addAttribute("error", "ERROR ERROR ERROR");
            return "error";
        }
    }

// ###########################################################################################
// #####                    Métodos con Postman                                         ######
// ###########################################################################################
    @PostMapping ("/create-update/videojuego/postman/{id}")
    public ResponseEntity<?> guardarVideojuegoPostman(@RequestBody Videojuego videojuego, @PathVariable("id") long id){
        try{
            System.out.println("Guardando desde postman");
            System.out.println("ID: " + (id));
            if (id == 0){
                System.out.println("ID == 0: " + (id));
                this.videojuegoService.saveOne(videojuego);
            } else {
                System.out.println("ID != 0: " + (id));
                this.videojuegoService.updateOne(videojuego,id);
            }
            return ResponseEntity.status(HttpStatus.OK).body(videojuego);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error");
        }
    }

    @GetMapping("/get/videojuegos/postman")
    public ResponseEntity<?> getVideojuegos() throws Exception{
        try{
            List<Videojuego> videojuegoList = this.videojuegoService.findAll();
            if (!videojuegoList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(videojuegoList);
            } else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al trear la lista");
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }



}
