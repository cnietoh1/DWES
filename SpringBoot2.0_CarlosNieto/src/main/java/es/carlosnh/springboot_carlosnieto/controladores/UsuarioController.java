package es.carlosnh.springboot_carlosnieto.controladores;

import es.carlosnh.springboot_carlosnieto.entidades.Perfil;
import es.carlosnh.springboot_carlosnieto.entidades.Usuario;
import es.carlosnh.springboot_carlosnieto.servicios.PerfilService;
import es.carlosnh.springboot_carlosnieto.servicios.UsuarioService;
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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;
  private final PerfilService perfilService;


  @GetMapping("/login")
  public String login(){
    return "usuario/login";
  }

  @GetMapping("/logout")
  public String logout(){
    return "redirect:/index";
  }

  @ModelAttribute("listaPerfiles")
  public List<Perfil> listaPerfiles() {
    return perfilService.findAll();
  }

  @GetMapping("/signup")
  public String signup(Model model){
    model.addAttribute("usuarioDto", new Usuario());
    return "usuario/signup";
  }

  @PostMapping("/signup")
  public String signupSubmit(@Valid @ModelAttribute("usuarioDto") Usuario nuevoUsuario,
                                   BindingResult bindingResult,
                                   Model model) {
    if (bindingResult.hasErrors()) {
      log.info("hay errores en el formulario");
      bindingResult.getFieldErrors()
              .forEach(e -> log.info("field: " + e.getField() + ", rejected value: " + e.getRejectedValue()));
      return "usuario/form";
    } else {
      Usuario usuario = usuarioService.findByUsernameOrEmail(nuevoUsuario.getUsername(), nuevoUsuario.getEmail());
      if (usuario != null) { // el usuario ya existe
        bindingResult.rejectValue("username", "username.existente",
                "ya existe un usuario con ese username");
        return "usuario/signup";
      }
      usuarioService.save(nuevoUsuario);
      return "redirect:/usuario/login";

    }
  }

}
