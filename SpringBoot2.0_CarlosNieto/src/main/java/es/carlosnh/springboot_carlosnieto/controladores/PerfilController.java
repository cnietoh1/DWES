package es.carlosnh.springboot_carlosnieto.controladores;

import es.carlosnh.springboot_carlosnieto.entidades.Perfil;
import es.carlosnh.springboot_carlosnieto.servicios.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/perfil")
public class PerfilController {

  private final PerfilService perfilService;

  @GetMapping({"/", "/list"})
  public String listado(Model model) {
    model.addAttribute("listaPerfiles", perfilService.findAll() );
    return "perfil/list";
  }


  @GetMapping("/new")
  public String nuevo(Model model){
    model.addAttribute("perfilDto", new Perfil());
    return "perfil/form";
  }

  @PostMapping("/new")
  public String nuevoSubmit(@Valid @ModelAttribute("perfilDto") Perfil nuevoPerfil,
                                   BindingResult bindingResult,
                                   Model model) {
    if (bindingResult.hasErrors()) {
      log.info("hay errores en el formulario");
      bindingResult.getFieldErrors()
              .forEach(e -> log.info("field: " + e.getField() + ", rejected value: " + e.getRejectedValue()));
      return "perfil/form";
    } else {
      Optional<Perfil> perfil = perfilService.findByNombre(nuevoPerfil.getNombre());
      if (perfil.isPresent()) { // el perfil ya existe
        bindingResult.rejectValue("nombre", "nombre.existente",
                "ya existe un perfil con ese nombre");
        return "perfil/form";
      }
      perfilService.save(nuevoPerfil);
      return "redirect:/perfil/list";

    }
  }

}
