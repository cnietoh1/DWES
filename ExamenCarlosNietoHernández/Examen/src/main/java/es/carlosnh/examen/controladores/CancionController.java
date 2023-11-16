package es.carlosnh.examen.controladores;

import es.carlosnh.examen.entidades.Cancion;
import es.carlosnh.examen.servicios.CancionService;
import es.carlosnh.examen.servicios.I18nService;
import es.carlosnh.examen.storage.StorageService;
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

@Slf4j
@RequiredArgsConstructor
@RequestMapping("song")
@Controller
public class CancionController {

    private final CancionService servicio;
    private final I18nService servicioInternacionalizacion;
    private final StorageService servicioAlmacenamiento;

    @GetMapping({"/", "/list"})
    public String listado(Model model) {
        model.addAttribute("listaCanciones", servicio.findAll() );
        return "list";
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
        } else if (servicio.findById(nuevaCancion.getId()) != null) {
            log.info("id repetido");
            model.addAttribute("modoEdicion", false);
            bindingResult.rejectValue("id", "id.existente", "ya existe este id");
            return "form";
        } else {
            log.info("añadimos cancion");
            servicio.add(nuevaCancion);
            return "redirect:/song/list";
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = servicioAlmacenamiento.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }
}
