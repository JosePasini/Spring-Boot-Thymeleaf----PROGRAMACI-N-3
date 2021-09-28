package com.example.videojuegos.controllers;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.services.CategoriaService;
import com.example.videojuegos.services.EstudioService;
import com.example.videojuegos.services.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
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

    @PostMapping ("/formulario/videojuego/{id}")
    public String guardarVideojuego(
            @RequestParam("archivo") MultipartFile archivo,
            @Valid @ModelAttribute("videojuegos") Videojuego videojuego,
            BindingResult result,
            Model model, @PathVariable("id") long id){

        try{
            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("estudios", this.estudioService.findAll());
            if (result.hasErrors()){
                return "views/formulario/videojuego";
            }

            // Acá comienza la carga de las imágenes
            // Luego tengo que eliminar los Souts y dejarlo más limpio
            // Tembién hay que ver qué ruta dejar.
            String ruta = "C://Videojuegos/imagenes";
            int index = archivo.getOriginalFilename().indexOf(".");
            String extension = "";
            extension = "."+archivo.getOriginalFilename().substring(index+1);
            System.out.println("Extension: " + extension);
            String nombreFoto = Calendar.getInstance().getTimeInMillis() + extension;
            System.out.println("Nombre Foto: " + nombreFoto);
            Path rutaAbsoluta = id != 0 ? Paths.get(ruta + "//" + videojuego.getRuta_img()) : Paths.get(ruta + "//"+nombreFoto);
            System.out.println("Path (ruta absoluta): " + rutaAbsoluta);
            if (id == 0){

                //Verifico imagen vacía
                if (archivo.isEmpty()){
                    model.addAttribute("imageErrorMsg", "La imagen es requerida");
                    return "views/formulario/videojuego";
                }
                //Verifico extensión de imagen
                if (!this.validarExtension(archivo)){
                    model.addAttribute("imageErrorMsg", "La extensión no es válida");
                    return "views/formulario/videojuego";
                }
                //Verifico peso de la imagen
                if (archivo.getSize() >= 15000000){
                    model.addAttribute("imageErrorMsg", "Excede lo permitido (15 MB)");
                    return "views/formulario/videojuego";
                }
                Files.write(rutaAbsoluta, archivo.getBytes());
                videojuego.setRuta_img(nombreFoto);
                this.videojuegoService.saveOne(videojuego);
            } else {
                if (!archivo.isEmpty()){
                    Files.write(rutaAbsoluta, archivo.getBytes());
                    this.videojuegoService.updateOne(videojuego,id);
                }
            }
            return "redirect:/crud";
        } catch (Exception e){
            model.addAttribute("error", "ERROR ERROR ERROR");
            return "error";
        }
    }

    @GetMapping("/eliminar/videojuego/{id}")
    public String eliminarVideojuego(Model model, @PathVariable("id") long id){
        try{
            model.addAttribute("videojuegos" , this.videojuegoService.findById(id));
            return "views/formulario/eliminar";
        } catch (Exception e){
            model.addAttribute("error", "ERROR ERROR ERROR");
            return "error";
        }
    }

    @PostMapping("/eliminar/videojuego/{id}")
    public String desactivarVideojuego(Model model, @PathVariable("id") long id){
        try{
            this.videojuegoService.deleteById(id);
            return "redirect:/crud";
        } catch (Exception e){
            model.addAttribute("error", "ERROR ERROR ERROR");
            return "error";
        }
    }

    public boolean validarExtension(MultipartFile archivo) throws Exception{
        try{
            ImageIO.read(archivo.getInputStream()).toString();
            return true;
        }catch (Exception e){
            System.out.println("Error en imagen: " + e);
            return false;
        }
    }


// ###########################################################################################
// #####                    Métodos con Postman                                         ######
// ###########################################################################################
    @PostMapping ("/create-update/videojuego/postman/{id}")
    public ResponseEntity<?> guardarVideojuegoPostman(@RequestBody Videojuego videojuego, @PathVariable("id") long id){
        try{
            if (id == 0){
                this.videojuegoService.saveOne(videojuego);
            } else {
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
