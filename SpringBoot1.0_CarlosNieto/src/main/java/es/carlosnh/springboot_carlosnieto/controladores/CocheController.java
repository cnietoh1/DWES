package es.carlosnh.springboot_carlosnieto.controladores;

import es.carlosnh.springboot_carlosnieto.entidades.Coche;
import es.carlosnh.springboot_carlosnieto.servicios.CocheService;
import es.carlosnh.springboot_carlosnieto.servicios.I18nService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("coche")
@Controller
public class CocheController {

    private final CocheService servicio;
    private final I18nService servicioInternacionalizacion;

    @GetMapping ({"/", "/list"})
    public String listado(Model model) {
        model.addAttribute("listaCoches", servicio.findAll());
        return "list";
    }

    @GetMapping("/new")
    public String nuevoCoche(Model model) {
         model.addAttribute("cocheDto", new Coche());
        model.addAttribute("modoEdicion", false);
         return "form";
    }

    @PostMapping("/new/submit")
    public String nuevoCocheSubmit(@Valid @ModelAttribute("cocheDto") Coche nuevoCoche,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "form";
        } else if (servicio.findById(nuevoCoche.getId()) != null) {
            model.addAttribute("modoEdicion", false);
            bindingResult.rejectValue("id", "id.existente", "ID existente");
            return "form";
        } else {
            servicio.add(nuevoCoche);
            return "redirect:/coche/list";
        }
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
    public String editarCocheSubmit(@Valid @ModelAttribute("cocheDto") Coche coche,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "form";
        } else {
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
        );
        model.addAttribute("delete_message", deleteMessage);
        return "fragmentos/delete-modal::modal-borrar";
    }
}
