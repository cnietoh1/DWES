package es.calosnh.examendwes.controladores;

import es.calosnh.examendwes.entidades.Cancion;
import es.calosnh.examendwes.entidades.Genero;
import es.calosnh.examendwes.servicios.CancionService;
import es.calosnh.examendwes.servicios.GeneroService;
import es.calosnh.examendwes.servicios.I18nService;
import es.calosnh.examendwes.storage.StorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("song")
@Controller
public class CancionController {

    private final CancionService cancionService;
    private final I18nService servicioInternacionalizacion;
    private final StorageService servicioAlmacenamiento;
    private final GeneroService generoService;

    @ModelAttribute("listaGeneros")
    public List<Genero> listaGeneros() {
        return generoService.findAll();
    }

    @GetMapping({"/", "/list"})
    public String listado(Model model) {
        model.addAttribute("listaCanciones", cancionService.findAll() );
        return "list";
    }

    @GetMapping( "/list/filter")
    public String listadoFiltrado(@RequestParam("titulo") String titulo, Model model) {
        model.addAttribute("listaCanciones", cancionService.findByTitulo(titulo));
        return "fragmentos/listaCanciones::listaCanciones";
    }

    @GetMapping("/new")
    public String nuevaCancion(Model model) {
        log.info("estoy en nuevaCancion");

        model.addAttribute("cancionDto", new Cancion());
        model.addAttribute("modoEdicion", false);
        return "form";
    }

    @PostMapping("/new/submit")
    public String nuevaCancionSubmit(@RequestParam(value = "fichero", required = false) MultipartFile fichero,
                                     @Valid @ModelAttribute("cancionDto") Cancion nuevaCancion,
                                     BindingResult bindingResult,
                                     Model model) {
        if (bindingResult.hasErrors()) {
            log.info("hay errores en el formulario");
            model.addAttribute("modoEdicion", false);
            bindingResult.getFieldErrors()
                    .forEach(e -> log.info("field: " + e.getField() + ", rejected value: " + e.getRejectedValue()));
            return "form";
        } else if (cancionService.findById(nuevaCancion.getId()) != null) {
            log.info("id repetido");
            model.addAttribute("modoEdicion", false);
            bindingResult.rejectValue("id", "id.existente", "ya existe este id");
            return "form";
        } else {
            if (!fichero.isEmpty()){
                log.info("hay foto");
                String fotoFilename = servicioAlmacenamiento.store(fichero, nuevaCancion.getId());
                nuevaCancion.setFoto(MvcUriComponentsBuilder
                        .fromMethodName(CancionController.class, "serveFile", fotoFilename).build().toUriString());
                log.info("foto de la cancion {}", nuevaCancion.getFoto());

            }
            log.info("añadimos cancion");
            cancionService.save(nuevaCancion);
            return "redirect:/song/list";
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = servicioAlmacenamiento.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }

    @GetMapping("/edit/{id}")
    public String editarCancionForm(@PathVariable long id, Model model) {

        Optional<Cancion> cancion = cancionService.findById(id);
        if (cancion.isPresent()) {
            model.addAttribute("cancionDto", cancion.get());
            model.addAttribute("modoEdicion", true);
            return "form";
        } else {
            return "redirect:/song/new";
        }
    }

    @PostMapping("/edit/submit")
    public String editarCancionSubmit(@RequestParam(value = "fichero", required = false) MultipartFile fichero,
                                      @Valid @ModelAttribute("cancionDto") Cancion cancion,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "form";
        } else {
            if (!fichero.isEmpty()){
                log.info("hay foto");
                String fotoFilename = servicioAlmacenamiento.store(fichero, cancion.getId());
                cancion.setFoto(MvcUriComponentsBuilder
                        .fromMethodName(CancionController.class, "serveFile", fotoFilename).build().toUriString());
            }
            cancionService.save(cancion);
            return "redirect:/song/list";
        }
    }

    @GetMapping("/delete/{id}")
    public String borrarCancion(@PathVariable("id") Long id) {

        Optional<Cancion> cancion = cancionService.findById(id);
        if (cancion.isPresent())
            cancionService.delete(cancion.get());

        return "redirect:/song/list";
    }

    @GetMapping("/delete/show/{id}")
    public String showModalBorrarCancion(@PathVariable("id") Long id, Model model) {

        Optional<Cancion> cancion = cancionService.findById(id);
        String deleteMessage;
        if (cancion.isPresent())
            deleteMessage = servicioInternacionalizacion.getMessage("cancion.borrar.mensaje",
                    new Object[]{cancion.get().getTitulo()} );
        else
            return "redirect:/song/?error=true";

        model.addAttribute("delete_url", "/song/delete/" + id);
        model.addAttribute("delete_title",
                servicioInternacionalizacion.getMessage("cancion.borrar.titulo")
        );
        model.addAttribute("delete_message", deleteMessage);
        return "fragmentos/delete-modal::modal-borrar";
    }
}
