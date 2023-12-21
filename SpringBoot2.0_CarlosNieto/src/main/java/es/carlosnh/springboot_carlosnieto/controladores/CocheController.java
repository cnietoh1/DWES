package es.carlosnh.springboot_carlosnieto.controladores;

import es.carlosnh.springboot_carlosnieto.entidades.Coche;
import es.carlosnh.springboot_carlosnieto.servicios.CocheService;
import es.carlosnh.springboot_carlosnieto.servicios.I18nService;
import es.carlosnh.springboot_carlosnieto.storage.StorageService;
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

@Slf4j
@RequiredArgsConstructor
@RequestMapping("coche")
@Controller
public class CocheController {

    private final CocheService servicio;
    private final I18nService servicioInternacionalizacion;
    private final StorageService servicioAlmacenamiento;

    @GetMapping ({"/", "/list"})
    public String listado(Model model) {
        model.addAttribute("listaCoches", servicio.findAll());
        return "list";
    }

    @GetMapping("/new")
    public String nuevoCoche(Model model) {
        log.info("estoy en nuevoCoche");

        model.addAttribute("cocheDto", new Coche());
        model.addAttribute("modoEdicion", false);
         return "form";
    }

    @PostMapping("/new/submit")
    public String nuevoCocheSubmit(@RequestParam(value = "fichero", required = false) MultipartFile fichero,
                                     @Valid @ModelAttribute("cocheDto") Coche nuevoCoche,
                                     BindingResult bindingResult,
                                     Model model) {
        if (bindingResult.hasErrors()) {
            log.info("hay errores en el formulario");
            model.addAttribute("modoEdicion", false);
            bindingResult.getFieldErrors()
                    .forEach(e -> log.info("field: " + e.getField() + ", rejected value: " + e.getRejectedValue()));
            return "form";
        } else if (servicio.findById(nuevoCoche.getId()) != null) {
            log.info("id repetido");
            model.addAttribute("modoEdicion", false);
            bindingResult.rejectValue("id", "id.existente", "ya existe este id");
            return "form";
        } else {
            if (!fichero.isEmpty()){
                log.info("hay foto");
                String fotoFilename = servicioAlmacenamiento.store(fichero, nuevoCoche.getId());
                nuevoCoche.setFoto(MvcUriComponentsBuilder
                        .fromMethodName(CocheController.class, "serveFile", fotoFilename).build().toUriString());
                log.info("uri de la foto del coche {}", nuevoCoche.getFoto());

            }
            log.info("añadimos coche");
            servicio.add(nuevoCoche);
            return "redirect:/coche/list";
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = servicioAlmacenamiento.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }

    @GetMapping("/edit/{id}")
    public String editarCocheForm(@PathVariable long id, Model model) {

        Coche coche = servicio.findById(id);
        if (coche != null) {
            model.addAttribute("cocheDto", coche);
            model.addAttribute("modoEdicion", true);
            return "form";
        } else {
            return "redirect:/coche/new";
        }
    }

    @PostMapping("/edit/submit")
    public String editarCocheSubmit(@RequestParam(value = "fichero", required = false) MultipartFile fichero,
                                      @Valid @ModelAttribute("mascotaDto") Coche coche,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "form";
        } else {
            if (!fichero.isEmpty()){
                log.info("hay foto");
                String fotoFilename = servicioAlmacenamiento.store(fichero, coche.getId());
                coche.setFoto(MvcUriComponentsBuilder
                        .fromMethodName(CocheController.class, "serveFile", fotoFilename).build().toUriString());

            }

            servicio.edit(coche);
            return "redirect:/coche/list";
        }
    }

    @GetMapping("/delete/{id}")
    public String borrarCoche(@PathVariable("id") Long id, Model model) {

        Coche coche = servicio.findById(id);
        if (coche != null)
            servicio.delete(coche);

        return "redirect:/coche/list";
    }

    @GetMapping("/delete/show/{id}")
    public String showModalBorrarCoche(@PathVariable("id") Long id, Model model) {

        Coche coche = servicio.findById(id);
        String deleteMessage = "";
        if (coche != null)
            deleteMessage = servicioInternacionalizacion.getMessage("coche.borrar.mensaje",
                    new Object[]{coche.getMarca(), coche.getModelo()} );
            //deleteMessage = "¿Confirma el borrado del vehículo " + coche.getId() + " ?";
        else
            return "redirect:/coche/?error=true";

        model.addAttribute("delete_url", "/coche/delete/" + id);
        model.addAttribute("delete_title",
                servicioInternacionalizacion.getMessage("coche.borrar.titulo")
                // "Borrar coche"
        );
        model.addAttribute("delete_message", deleteMessage);
        return "fragmentos/delete-modal::modal-borrar";
    }
}
